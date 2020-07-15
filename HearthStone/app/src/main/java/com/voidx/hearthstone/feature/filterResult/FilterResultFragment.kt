package com.voidx.hearthstone.feature.filterResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.voidx.hearthstone.databinding.FragmentFilterResultBinding
import com.voidx.hearthstone.feature.filterResult.coordinator.FilterResultCoordinator
import com.voidx.presentation.filterResults.FilterResultsViewModel
import com.voidx.presentation.state.State
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FilterResultFragment : Fragment() {

    private val adapter: FilterResultAdapter by lazy {
        FilterResultAdapter()
    }

    private val viewModel: FilterResultsViewModel by viewModel {
        parametersOf(args.category, args.type)
    }

    private val coordinator: FilterResultCoordinator by lifecycleScope.inject {
        parametersOf(findNavController())
    }

    private val args: FilterResultFragmentArgs by navArgs()

    private lateinit var binding: FragmentFilterResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentFilterResultBinding.inflate(LayoutInflater.from(context), container, false)

        binding.apply {
            viewModel = this@FilterResultFragment.viewModel
            lifecycleOwner = this@FilterResultFragment
        }

        binding.list.apply {
            adapter = this@FilterResultFragment.adapter
        }

        binding.back.setOnClickListener {
            coordinator.back()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading()
                is State.Success -> showList()
            }
        })
        lifecycle.addObserver(viewModel)
    }

    private fun showList() {
        binding.list.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.list.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }
}
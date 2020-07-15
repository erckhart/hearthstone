package com.voidx.hearthstone.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.voidx.hearthstone.databinding.FragmentListGameOptionsBinding
import com.voidx.hearthstone.feature.list.coordinator.ListGameOptionsCoordinator
import com.voidx.presentation.list.ListGameInfoViewModel
import com.voidx.presentation.state.State
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

typealias OnItemClick<T> = ((item: T) -> Unit)

class ListGameOptionsFragment : Fragment() {

    private lateinit var binding: FragmentListGameOptionsBinding

    private val viewModel: ListGameInfoViewModel by viewModel()

    private val coordinator: ListGameOptionsCoordinator by lifecycleScope.inject { parametersOf(findNavController()) }

    private val adapter by lazy {
        ListGameOptionsAdapter {
            coordinator.showDetails(it.first.title, it.second.title)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListGameOptionsBinding.inflate(inflater, container, false)
        binding.apply {
            this.viewModel = this@ListGameOptionsFragment.viewModel
            this.lifecycleOwner = this@ListGameOptionsFragment
        }

        binding.list.apply {
            adapter = this@ListGameOptionsFragment.adapter
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
        binding.list.visibility = VISIBLE
        binding.loading.visibility = GONE
    }

    private fun showLoading() {
        binding.list.visibility = GONE
        binding.loading.visibility = VISIBLE
    }
}
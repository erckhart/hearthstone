package com.voidx.hearthstone.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.voidx.hearthstone.databinding.FragmentListGameOptionsBinding
import com.voidx.hearthstone.view.widget.recyclerview.MarginItemDecorator
import com.voidx.presentation.list.ListGameInfoViewModel
import com.voidx.presentation.state.State
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

class ListGameOptionsFragment : Fragment() {

    private lateinit var binding: FragmentListGameOptionsBinding

    private lateinit var listGameInfoViewModel: ListGameInfoViewModel

    private val adapter by lazy { ListGameOptionsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        listGameInfoViewModel = getViewModel(this, ListGameInfoViewModel::class.java)

        binding = FragmentListGameOptionsBinding.inflate(inflater, container, false)
        binding.apply {
            this.viewModel = listGameInfoViewModel
            this.lifecycleOwner = this@ListGameOptionsFragment
        }

        binding.list.apply {
            adapter = this@ListGameOptionsFragment.adapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listGameInfoViewModel.state().observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading()
                is State.Success -> showList()
            }
        })
        lifecycle.addObserver(listGameInfoViewModel)
    }

    private fun showList() {
        binding.content.visibility = VISIBLE
        binding.loading.visibility = GONE
    }

    private fun showLoading() {
        binding.content.visibility = GONE
        binding.loading.visibility = VISIBLE
    }
}
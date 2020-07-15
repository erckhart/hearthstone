package com.voidx.hearthstone.feature.filterResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidx.hearthstone.databinding.FilterResultItemBinding
import com.voidx.hearthstone.view.widget.recyclerview.binding.BindableViewHolder
import com.voidx.hearthstone.view.widget.recyclerview.binding.RecyclerViewBinding
import com.voidx.presentation.dto.ResultDTO

class FilterResultAdapter : RecyclerView.Adapter<BindableViewHolder<FilterResultItemBinding>>(),
    RecyclerViewBinding.BindableAdapter<List<ResultDTO>> {

    private var items: List<ResultDTO> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<FilterResultItemBinding> {

        val binding =
            FilterResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BindableViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: BindableViewHolder<FilterResultItemBinding>,
        position: Int
    ) {
        holder.viewDataBinding.result = items[position]
    }

    override fun setData(data: List<ResultDTO>?) {
        items = data ?: emptyList()
        notifyDataSetChanged()
    }

}
package com.voidx.hearthstone.feature.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidx.hearthstone.databinding.GameOptionItemBinding
import com.voidx.hearthstone.view.binding.BindableViewHolder
import com.voidx.hearthstone.view.binding.RecyclerViewBinding
import com.voidx.presentation.dto.GameOptionItemDTO

class ListGameOptionsItemAdapter :
    RecyclerView.Adapter<BindableViewHolder<GameOptionItemBinding>>(),
    RecyclerViewBinding.BindableAdapter<List<GameOptionItemDTO>> {

    private var items: List<GameOptionItemDTO> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindableViewHolder<GameOptionItemBinding> {
        val binding =
            GameOptionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BindableViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: BindableViewHolder<GameOptionItemBinding>,
        position: Int
    ) {
        holder.viewDataBinding.gameOption = items[position]
    }

    override fun setData(data: List<GameOptionItemDTO>?) {
        items = data ?: emptyList()
        notifyDataSetChanged()
    }
}
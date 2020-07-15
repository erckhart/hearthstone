package com.voidx.hearthstone.feature.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidx.hearthstone.databinding.GameOptionItemBinding
import com.voidx.hearthstone.feature.list.OnItemClick
import com.voidx.hearthstone.view.widget.recyclerview.binding.BindableViewHolder
import com.voidx.hearthstone.view.widget.recyclerview.binding.RecyclerViewBinding
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.dto.GameOptionItemDTO

class ListGameOptionsItemAdapter(private val itemClick: OnItemClick<GameOptionItemDTO>) :
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
        val item = items[position]
        holder.viewDataBinding.gameOption = item
        holder.viewDataBinding.root.setOnClickListener { itemClick.invoke(item) }
    }

    override fun setData(data: List<GameOptionItemDTO>?) {
        items = data ?: emptyList()
        notifyDataSetChanged()
    }
}
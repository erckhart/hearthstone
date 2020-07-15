package com.voidx.hearthstone.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidx.hearthstone.databinding.GameInfoItemBinding
import com.voidx.hearthstone.feature.list.item.ListGameOptionsItemAdapter
import com.voidx.hearthstone.view.widget.recyclerview.MarginItemDecorator
import com.voidx.hearthstone.view.widget.recyclerview.binding.BindableViewHolder
import com.voidx.hearthstone.view.widget.recyclerview.binding.RecyclerViewBinding
import com.voidx.presentation.dto.GameOptionDTO
import com.voidx.presentation.dto.GameOptionItemDTO

class ListGameOptionsAdapter(private val itemClick: OnItemClick<Pair<GameOptionDTO, GameOptionItemDTO>>) :
    RecyclerView.Adapter<ListGameOptionsHolder>(),
    RecyclerViewBinding.BindableAdapter<List<GameOptionDTO>> {

    private var options: List<GameOptionDTO> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListGameOptionsHolder {
        val binding =
            GameInfoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListGameOptionsHolder(binding)
    }

    override fun getItemCount(): Int = options.size

    override fun setData(data: List<GameOptionDTO>?) {
        options = data ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListGameOptionsHolder, position: Int) {
        val option = options[position]
        holder.viewDataBinding.gameOption = option
        holder.itemClick = {
            itemClick.invoke(Pair(option, it))
        }
    }

}

class ListGameOptionsHolder(
    binding: GameInfoItemBinding
): BindableViewHolder<GameInfoItemBinding>(binding) {

    var itemClick: OnItemClick<GameOptionItemDTO>? = null

    init {
        viewDataBinding.options.adapter = ListGameOptionsItemAdapter {
            itemClick?.invoke(it)
        }
        viewDataBinding.options.addItemDecoration(MarginItemDecorator(left = 16, right = 8, bottom = 24))
    }

}
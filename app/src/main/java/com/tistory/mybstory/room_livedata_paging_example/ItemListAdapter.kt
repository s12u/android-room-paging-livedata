package com.tistory.mybstory.room_livedata_paging_example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item.view.*

class ItemListAdapter : PagedListAdapter<Item, ItemListAdapter.ItemViewHolder>(diffCallback) {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        return ItemViewHolder(inflater!!.inflate(R.layout.layout_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Item?) {
            item?.let {
                itemView.tv_item_id.text =
                    String.format(itemView.resources.getString(R.string.item_placeholder), item.id)
            }
        }
    }
}
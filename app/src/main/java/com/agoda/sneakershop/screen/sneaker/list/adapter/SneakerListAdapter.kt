package com.agoda.sneakershop.screen.sneaker.list.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.agoda.sneakershop.R
import com.agoda.sneakershop.common.extension.format
import com.agoda.sneakershop.common.extension.setImageUrl
import com.agoda.sneakershop.screen.sneaker.list.viewmodel.SneakerListItemViewModel
import kotlinx.android.synthetic.main.item_sneaker.view.*
import kotlin.properties.Delegates

class SneakerListDiffUtilCallback(private val oldItems: List<SneakerListItemViewModel>,
                                  private val newItems: List<SneakerListItemViewModel>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].id == newItems[newItemPosition].id

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]

}

open class SneakerListAdapter : RecyclerView.Adapter<SneakerListViewHolder>() {

    interface Listener {
        fun onItemClick(position: Int)
    }

    open var items by Delegates.observable(listOf<SneakerListItemViewModel>()) { _, oldValue, newValue ->
        DiffUtil.calculateDiff(SneakerListDiffUtilCallback(oldValue, newValue)).dispatchUpdatesTo(this)
    }

    open var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sneaker, parent, false)
        return SneakerListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: SneakerListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

class SneakerListViewHolder(view: View, listener: SneakerListAdapter.Listener?) : RecyclerView.ViewHolder(view) {

    val tvItemSneakerName: TextView = itemView.tvItemSneakerName
    val ivItemSneakerImage: ImageView = itemView.ivItemSneakerImage
    val tvItemSneakerCategory: TextView = itemView.tvItemSneakerCategory
    val tvItemSneakerCollection: TextView = itemView.tvItemSneakerCollection
    val tvItemSneakerPrice: TextView = itemView.tvItemSneakerPrice

    init {
        listener?.let { l ->
            itemView.setOnClickListener { l.onItemClick(layoutPosition) }
        }
    }

    fun bind(viewModel: SneakerListItemViewModel) {
        viewModel.run {
            tvItemSneakerName.text = name
            ivItemSneakerImage.setImageUrl(imageUrl)
            tvItemSneakerCategory.text = categoryName
            tvItemSneakerCollection.text = collectionName
            tvItemSneakerPrice.text = "$ ${price.format()}"
        }
    }

}
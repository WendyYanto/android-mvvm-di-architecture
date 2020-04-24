package com.example.daggerkld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.R

class ProductListAdapter(
    private val productItemInterface: ProductItemInterface
) : ListAdapter<String, RecyclerView.ViewHolder>(DIFF_UTIL) {

    interface ProductItemInterface {
        fun click(index: Int)
        fun showProductEmptyText()
        fun hideProductEmptyText()
        fun scrollTop()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = holder as ProductItem
        productItem.bind(position)
    }

    fun isItemEmpty(): Boolean = currentList.isNullOrEmpty()

    override fun submitList(list: MutableList<String>?) {
        if (list.isNullOrEmpty()) {
            productItemInterface.showProductEmptyText()
        } else {
            productItemInterface.hideProductEmptyText()
        }
        super.submitList(list)
    }

    inner class ProductItem(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private var productItemTextView: TextView? = null

        fun bind(index: Int) {
            val title = currentList[index]
            productItemTextView = itemView.findViewById(R.id.tv_product_item_title)
            productItemTextView?.text = title
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) {
                    Toast.makeText(
                        itemView.context,
                        itemView.context.getString(R.string.please_wait_while_adapter_changes),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                productItemInterface.click(adapterPosition)
            }
            itemView.setOnLongClickListener {
                moveToTop()
                true
            }
        }

        private fun moveToTop() {
            layoutPosition.takeIf { it > 0 }?.also { position ->
                val data = currentList.toMutableList()
                data.removeAt(position)?.apply {
                    data.add(0, this)
                    submitList(data)
                    productItemInterface.scrollTop()
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}

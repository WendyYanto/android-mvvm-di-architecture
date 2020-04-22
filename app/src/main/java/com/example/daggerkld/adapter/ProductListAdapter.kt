package com.example.daggerkld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.R

class ProductListAdapter(
    private val productItemInterface: ProductItemInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ProductItemInterface {
        fun click(index: Int)
        fun showProductEmptyText()
        fun hideProductEmptyText()
    }

    private var data: MutableList<String>? = mutableListOf()

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

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun isItemEmpty(): Boolean = data.isNullOrEmpty()

    fun populateList(newData: List<String>) {
        if (newData.isNullOrEmpty()) {
            productItemInterface.showProductEmptyText()
        } else {
            productItemInterface.hideProductEmptyText()
        }
        data = newData.toMutableList()
        this.notifyDataSetChanged()
    }

    inner class ProductItem(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private var productItemTextView: TextView? = null

        fun bind(index: Int) {
            val title = data?.get(index)
            productItemTextView = itemView.findViewById(R.id.tv_product_item_title)
            productItemTextView?.text = title
            productItemTextView?.setOnClickListener {
                productItemInterface.click(index)
            }

            productItemTextView?.setOnLongClickListener {
                moveToTop()
                true
            }
        }

        private fun moveToTop() {
            layoutPosition.takeIf { it > 0 }?.also { position ->
                data?.removeAt(position)?.apply {
                    data?.add(0, this)
                    notifyItemMoved(position, 0)
                }
            }
        }
    }
}

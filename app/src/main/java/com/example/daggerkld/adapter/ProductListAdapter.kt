package com.example.daggerkld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.R

class ProductListAdapter(
    private val data: List<String>,
    private val productItemInterface: ProductItemInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ProductItemInterface {
        fun click(index: Int)
        fun showProductEmptyText()
        fun hideProductEmptyText()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product_item, parent, false)
        return ProductItem(view.rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = holder as ProductItem
        productItem.bind(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(index: Int): String {
        return data[index]
    }

    fun populateList(newData: List<String>) {
        if (newData.isNullOrEmpty()) {
            productItemInterface.showProductEmptyText()
        } else {
            productItemInterface.hideProductEmptyText()
        }
        this.notifyDataSetChanged()
    }

    inner class ProductItem(
        itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productItemTextView: TextView? = null

        fun bind(index: Int) {
            val title = data[index]
            productItemTextView = itemView.findViewById(R.id.tv_product_item_title)
            productItemTextView?.text = title
            productItemTextView?.setOnClickListener {
                productItemInterface.click(index)
            }
        }
    }
}

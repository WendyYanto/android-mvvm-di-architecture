package com.example.daggerkld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.R
import java.util.UUID

class ProductListAdapter(
    private val data: List<String>,
    private val productItemInterface: ProductItemInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ProductItemInterface {
        fun click(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product_item, parent, false)
        return ProductItem(view.rootView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = holder as ProductItem
        productItem.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ProductItem(
        itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var productItemTextView: TextView? = null

        fun bind(title: String) {
            productItemTextView = itemView.findViewById(R.id.tv_product_item_title)
            productItemTextView?.text = "${title}-${UUID.randomUUID()}"
            productItemTextView?.setOnClickListener {
                productItemInterface.click(title)
            }
        }
    }
}

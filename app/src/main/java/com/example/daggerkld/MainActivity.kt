package com.example.daggerkld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.adapter.ProductListAdapter
import com.example.daggerkld.di.MyApplication
import com.example.daggerkld.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var toastButton: Button
    private lateinit var productListRecycleView: RecyclerView
    private lateinit var productListAdapter: ProductListAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).applicationComponent.inject(this)
        this.init()
    }

    private fun init() {
        toastButton = findViewById(R.id.btn_show_toast)
        productListRecycleView = findViewById(R.id.rv_product_list)

        toastButton.setOnClickListener {
            Toast.makeText(this, "This is Testing Toast", Toast.LENGTH_SHORT).show()
        }

        productListRecycleView.layoutManager = LinearLayoutManager(this)

        productListAdapter = ProductListAdapter(
            this.presenter.fetchData(),
            object : ProductListAdapter.ProductItemInterface {
                override fun click(index: Int) {
                    removeData(index)
                }
            })
        productListRecycleView.adapter = productListAdapter
    }

    private fun removeData(index: Int) {
        Toast.makeText(this, "Removing index $index", Toast.LENGTH_SHORT).show()
        this.presenter.removeData(index)
        this.productListAdapter.notifyDataSetChanged()
    }

}

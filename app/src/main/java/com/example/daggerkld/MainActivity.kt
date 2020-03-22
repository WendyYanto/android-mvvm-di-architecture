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
import dagger.android.AndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var toastButton: Button
    private lateinit var productListRecycleView: RecyclerView

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MyApplication).applicationComponent.inject(this)
        this.init()
    }

    private fun init() {
        val list = ArrayList<String>()
        for (i in 0..100) {
            list.add(i.toString())
        }
        toastButton = findViewById(R.id.btn_show_toast)
        productListRecycleView = findViewById(R.id.rv_product_list)

        toastButton.setOnClickListener {
            Toast.makeText(this, "This is Testing Toast", Toast.LENGTH_SHORT).show()
            presenter.showLog()
        }

        productListRecycleView.layoutManager = LinearLayoutManager(this)
        productListRecycleView.adapter = ProductListAdapter(list, object: ProductListAdapter.ProductItemInterface {
            override fun click(title: String) {
                Toast.makeText(this@MainActivity, "From Product Item $title", Toast.LENGTH_SHORT).show()
            }
        })

    }



}

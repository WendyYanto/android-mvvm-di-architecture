package com.example.daggerkld

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.adapter.ProductListAdapter
import com.example.daggerkld.data.Response
import com.example.daggerkld.di.ViewModelProviderFactory
import com.example.daggerkld.extension.hide
import com.example.daggerkld.extension.show
import com.example.daggerkld.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var toastButton: Button
    private lateinit var productListRecycleView: RecyclerView
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var addProductTextView: EditText
    private lateinit var productStatusTextView: TextView
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.init()

        viewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)
        viewModel.fetchData().observe(this, Observer { response ->
            when (response) {
                is Response.Success -> productListAdapter.populateList(response.data)
                is Response.Error -> Toast.makeText(
                    this,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun init() {
        toastButton = findViewById(R.id.btn_show_toast)
        productListRecycleView = findViewById(R.id.rv_product_list)
        addProductTextView = findViewById(R.id.tv_add_product)
        productStatusTextView = findViewById(R.id.tv_product_status)

        addProductTextView.setOnKeyListener { _, keyCode, _ ->
            checkAndClickToastButton(keyCode)
            true
        }

        toastButton.setOnClickListener {
            val value = addProductTextView.text.trim()
            if (value.isNotBlank()) {
                this.viewModel.addData(value.toString())
            }
            addProductTextView.text = null
        }

        productListRecycleView.layoutManager = LinearLayoutManager(this)

        productListAdapter = ProductListAdapter(
            object : ProductListAdapter.ProductItemInterface {
                override fun showProductEmptyText() {
                    productStatusTextView.show()
                }

                override fun hideProductEmptyText() {
                    productStatusTextView.hide()
                }

                override fun click(index: Int) {
                    viewModel.removeData(index)
                }
            })
        productListRecycleView.adapter = productListAdapter
    }

    private fun checkAndClickToastButton(code: Int) {
        if (code == KeyEvent.KEYCODE_ENTER) {
            this.toastButton.performClick()
            this.hideKeyboard(this.toastButton)
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

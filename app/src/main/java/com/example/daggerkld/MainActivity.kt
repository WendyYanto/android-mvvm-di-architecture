package com.example.daggerkld

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkld.adapter.ProductListAdapter
import com.example.daggerkld.base.BaseActivity
import com.example.daggerkld.data.Response
import com.example.daggerkld.extension.hide
import com.example.daggerkld.extension.show
import com.example.daggerkld.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var toastButton: Button
    private lateinit var productListRecycleView: RecyclerView
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var addProductTextView: EditText
    private lateinit var productStatusTextView: TextView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.init()

        viewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MainViewModel::class.java)
        viewModel.fetchData().observe(this, Observer { response ->
            when (response) {
                is Response.Success -> {
                    if (productListAdapter.isItemEmpty().not() &&
                        productListAdapter.isPositionUpdate().not()
                    ) {
                        showNotification(response.data)
                    }
                    populateList(response.data.toMutableList())
                }
                is Response.Error -> {
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

        productListAdapter = ProductListAdapter(
            object : ProductListAdapter.ProductItemInterface {
                override fun showProductEmptyText() {
                    productStatusTextView.show()
                }

                override fun hideProductEmptyText() {
                    productStatusTextView.hide()
                }

                override fun moveTop(index: Int) {
                    viewModel.updateDataPosition(index, 0)
                }

                override fun click(index: Int) {
                    viewModel.removeData(index)
                }
            })

        with(productListRecycleView) {
            layoutManager = LinearLayoutManager(context)
            adapter = productListAdapter
        }
    }

    private fun checkAndClickToastButton(code: Int) {
        if (code == KeyEvent.KEYCODE_ENTER) {
            this.toastButton.performClick()
            this.hideKeyboard(this.toastButton)
        }
    }

    private fun showNotification(products: List<String>) {
        val inbox = NotificationCompat.InboxStyle().also { inbox ->
            products.forEach {
                inbox.addLine(it)
            }
        }

        val intentToMainActivity = Intent(this, MainActivity::class.java)

        val builder = NotificationCompat
            .Builder(this, Constants.PRODUCT_NOTIFICATION_CHANNEL)
            .setContentTitle(getString(R.string.product_notification_title, products.size))
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(inbox)
            .setColor(Color.GREEN)
            .setColorized(true)
            .setContentIntent(PendingIntent.getActivity(this, 0, intentToMainActivity, 0))
            .build()

        NotificationManagerCompat.from(this).apply {
            notify(NOTIFICATION_REQUEST_CODE, builder)
        }
    }

    private fun populateList(newList: MutableList<String>) {
        if (productListAdapter.isPositionUpdate()) {
            productListAdapter.submitList(newList) {
                productListRecycleView.scrollToPosition(0)
            }
        } else {
            productListAdapter.submitList(newList)
        }
        productListAdapter.setPositionUpdateState(false)
    }

    companion object {
        private const val NOTIFICATION_REQUEST_CODE = 1
    }

}

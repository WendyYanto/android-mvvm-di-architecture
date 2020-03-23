package com.example.daggerkld.extension

import android.view.View
import android.widget.TextView

fun TextView.show() {
    this.visibility = View.VISIBLE
}

fun TextView.hide() {
    this.visibility = View.GONE
}

fun TextView.toggleVisibility() {
    if (this.visibility == View.VISIBLE) {
        this.hide()
    } else {
        this.show()
    }
}
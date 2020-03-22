package com.example.daggerkld.presenter

import android.util.Log

class MainPresenterImpl: MainPresenter {
    override fun showLog() {
        Log.i("LoggerFromPresenter","Testing From MainPresenterImpl")
    }
}
package com.example.sainttropez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // HTMLを表示する
        showHTML()
    }

    private fun showHTML() {
        // JavaScriptを有効にする
        webView.settings.javaScriptEnabled = true
        // アプリ内のaasetsフォルダ先は「file//:android_asset/」でアクセスする
        webView.loadUrl("file//:android_asset/html/index.html")
    }
}

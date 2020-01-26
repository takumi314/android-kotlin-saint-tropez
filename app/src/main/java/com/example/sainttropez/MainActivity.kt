package com.example.sainttropez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // HTMLを表示する
        showHTML()
        // コンテキストメニューを登録する
        // - 登録したビューを長押しするとregisterForContextMenuをコールします
        registerForContextMenu(webView)
    }

    // オプションメニューを初期化する
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // メニューXMLの内容をメニューに設定する
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // コンテキストメニューが表示される時にコールされる
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // メニューXMLファイルを、コンテキストメニューに表示する
        menuInflater.inflate(R.menu.context, menu)
    }

    // コンテキストメニューが選択された時にコールされる
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sms -> return true
            R.id.mail -> return true
            R.id.share -> return true
            R.id.browse -> return true
        }
        return super.onContextItemSelected(item)
    }

    // オプションメニューが選択された時にコールされる
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.top -> {
                webView.loadUrl("file:///android_asset/html/index.html")
                return true
            }
            R.id.lunch01 -> {
                webView.loadUrl("file:///android_asset/html/lunch01.html")
                return true
            }
            R.id.lunch02 -> {
                webView.loadUrl("file:///android_asset/html/lunch02.html")
                return true
            }
            R.id.dinner01 -> {
                webView.loadUrl("file:///android_asset/html/dinner01.html")
                return true
            }
            R.id.dinner02 -> {
                webView.loadUrl("file:///android_asset/html/dinner02.html")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showHTML() {
        // JavaScriptを有効にする
        webView.settings.javaScriptEnabled = true
        // アプリ内のaasetsフォルダ先は「file//:android_asset/」でアクセスする
        webView.loadUrl("file:///android_asset/html/index.html")
    }
}

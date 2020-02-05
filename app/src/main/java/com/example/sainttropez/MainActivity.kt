package com.example.sainttropez

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
            R.id.sms -> {
                openSMSApp()
                return true
            }
            R.id.mail -> {
                openMailer()
                return true
            }
            R.id.share -> {
                openShareApp()
                return true
            }
            R.id.browse -> {
                openBrowser()
                return true
            }
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

    private fun openSMSApp() {
        val number = "111-1111-1111"
        val uri = Uri.parse("sms:$number")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra("sms_body", "こんにちは")    // 予めメッセージを入力することができる
        intent.data = uri
        startActivity(intent)
    }

    private fun openMailer() {
        val address = "XXX@YYY.ZZZ"
        val uri = Uri.parse("mailto:$address")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.type = "text/plain"
        intent.data = uri
        intent.putExtra(Intent.EXTRA_SUBJECT, "タイトル")
        intent.putExtra(Intent.EXTRA_TEXT, "本文です。")
        try {
            startActivity(intent)
        } catch (e: Exception) {
            print(e)
        }
    }

    private fun openShareApp() {
        val message = Uri.encode("ここに #コメント")
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse("twitter://post?massage=$message")
        intent.data = uri
        startActivityForResult(intent, 1)
    }

    private fun openBrowser() {
        val uri = Uri.parse("")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        try {
            startActivity(intent)
        } catch (e: Exception) {
            print(e)
        }
    }

}

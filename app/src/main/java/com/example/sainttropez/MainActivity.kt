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

    // ショートメッセージを送る
    private fun openSMSApp() {
        val number = "111-1111-1111"
        val uri = Uri.parse("sms:$number")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.putExtra("sms_body", "こんにちは")    // 予めメッセージを入力することができる
        intent.data = uri
        startActivity(intent)
    }

    // メールアプリを起動する
    private fun openMailer() {
        val email = "nobody@example.com"
        val subject = "予約問い合わせ"
        val text = "以下のとおり予約を希望します。"
        val uri = Uri.parse("mailto:")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = uri
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            .putExtra(Intent.EXTRA_SUBJECT, subject)
            .putExtra(Intent.EXTRA_TEXT, text)
        // アプリが未インストールしていない場合のための処理
        // - 処理できるアプリがないと呼び出しに失敗し、アプリが強制終了してしまうため
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    // 文字列を共有する
    private fun openShareApp() {
        val text = "美味しいレストランを紹介します。"
        val intent = Intent(Intent.ACTION_SEND)
        // インテントのタイプを設定する
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)    // 共有する文字列

        // インテント対象が複数ある場合にアプリ選択画面を表示するインテントを作成する
        // - target: アプリ選択画面の対象となるインテント
        // - title: アプリ選択画面に表示するタイトル、設定しない場合はnullを渡します
        val chooser = Intent.createChooser(intent, null)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
    }

    // ブラウザでURLを開く
    private fun openBrowser() {
        val url = "http://www.yahoo.co.jp/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)    // ブラウザに渡すURL
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}

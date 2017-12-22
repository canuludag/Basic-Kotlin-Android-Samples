package com.uludag.can.kotlincamerasampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view_example.*

class WebViewExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_example)
        setupSupportActionbar()

        sendButton.setOnClickListener {
            loadWebpage()
        }

    }

    private fun loadWebpage() {
        webView.loadUrl(urlEditText.text.toString())
    }

    private fun setupSupportActionbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "WebView Example"
    }
}

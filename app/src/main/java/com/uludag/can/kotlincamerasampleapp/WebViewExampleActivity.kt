package com.uludag.can.kotlincamerasampleapp

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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
        webView.settings.javaScriptEnabled = true
        val uri = buildUri(urlEditText.text.toString())
        webView.loadUrl(uri.toString())
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    @Throws(UnsupportedOperationException::class)
    private fun buildUri(authority: String) : Uri {
        val builder = Uri.Builder()
        builder.scheme("https")
                .authority(authority)
        return builder.build()
    }

    private fun setupSupportActionbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = "WebView Example"
    }
}

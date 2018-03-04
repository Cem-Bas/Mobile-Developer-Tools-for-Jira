package com.cembas.mobiledevelopertoolsforjira

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient

class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var siteButton = findViewById<View>(R.id.siteButton)

        var webView: WebView = findViewById(R.id.siteWebView)


        siteButton.setOnClickListener {

            val webView = WebView(this)
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()

            webView.loadUrl("https://www.jiradevtools.com")
            setContentView(webView)


        }

    }
}

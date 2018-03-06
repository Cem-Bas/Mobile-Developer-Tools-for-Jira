package com.cembas.mobiledevelopertoolsforjira

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.support.v4.app.NavUtils
import android.view.MenuItem


class About : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

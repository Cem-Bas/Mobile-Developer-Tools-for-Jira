package com.cembas.mobiledevelopertoolsforjira

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.system.Os.link
import com.cembas.mobiledevelopertoolsforjira.R.id.webView
import android.webkit.WebViewClient
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var jiraTicketNumber: EditText = findViewById(R.id.jiraTicketNumber)

        var goButton: Button = findViewById(R.id.goButton)

        var webView: WebView = findViewById(R.id.webView)

        goButton.setOnClickListener {

            val ticketNumberValue: String = jiraTicketNumber.text.toString()

            val webView = WebView(this)
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://jiradevtools.atlassian.net/browse/"+ticketNumberValue)
            setContentView(webView)

            jiraTicketNumber.inputType = 0


    }
    }
}

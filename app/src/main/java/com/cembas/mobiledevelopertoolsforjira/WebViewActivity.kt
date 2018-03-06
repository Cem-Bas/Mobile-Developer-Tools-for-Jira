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
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager


class WebViewActivity : AppCompatActivity() {

    val SHARED_PREF_FILE = "com.cembas.mobiledevelopertoolsforjira.appdata"
    var preffs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var jiraTicketNumber: EditText = findViewById(R.id.jiraTicketNumber)

        var goButton: Button = findViewById(R.id.goButton)

        var webView: WebView = findViewById(R.id.webView)

        preffs = this.getSharedPreferences(SHARED_PREF_FILE, 0)


        goButton.setOnClickListener {

            val ticketNumberValue: String = jiraTicketNumber.text.toString()

            val webView = WebView(this)
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()

            val jiraAddressPreffs = preffs?.getString("JiraAddress", "")

            val baseUrl = "https://"+jiraAddressPreffs


            webView.loadUrl(baseUrl+"/browse/"+ticketNumberValue)
            setContentView(webView)

            jiraTicketNumber.inputType = 0


    }
    }
}

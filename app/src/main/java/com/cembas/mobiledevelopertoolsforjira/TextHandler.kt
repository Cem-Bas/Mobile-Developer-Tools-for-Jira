package com.cembas.mobiledevelopertoolsforjira

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.util.*

class TextHandler : AppCompatActivity() {

    companion object {
        const val REQUEST_PERMISSION = 1
    }

    val SHARED_PREF_FILE = "com.cembas.mobiledevelopertoolsforjira.appdata"
    var preffs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_text)

        var addComment: Button = findViewById(R.id.addComment)

        var ticketBody: EditText = findViewById(R.id.ticket)
        var commentBody: EditText = findViewById(R.id.commentBody)

        preffs = this.getSharedPreferences(SHARED_PREF_FILE, 0)

        try {
            addComment.setOnClickListener {

                val comment = commentBody.text.toString()

                val ticketNumber = ticketBody.text.toString()

                val jiraAddressPreffs = preffs?.getString("JiraAddress", "")

                val credentials = preffs?.getString("credentials", "")

                val baseUrl = "https://"+jiraAddressPreffs

                Log.d("COMMENT LOGS", "BEFORE")

                SendComment().execute(credentials, baseUrl, ticketNumber, comment)

                Log.d("COMMENT LOGS", "AFTER")

                Thread.sleep(2000)
                Log.d("COMMENT LOGS", comment)

                Toast.makeText(this@TextHandler, "Comment Sent", Toast.LENGTH_LONG).show()

                val intent = Intent(this@TextHandler, MainActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("COMMENT LOGS", e.printStackTrace().toString())
        }
    }
    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), TextHandler.REQUEST_PERMISSION)
        } else {
            write()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            TextHandler.REQUEST_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                write()
            }
        }
    }

    private fun write() {
        val dir = "${Environment.getExternalStorageDirectory()}/$packageName"
        File(dir).mkdirs()
        val file = "%1\$tY%1\$tm%1\$td%1\$tH%1\$tM%1\$tS.log".format(Date())
        File("$dir/$file").printWriter().use {
            it.println("text")
        }
    }
}

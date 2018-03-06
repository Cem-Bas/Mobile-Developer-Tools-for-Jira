package com.cembas.mobiledevelopertoolsforjira

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import java.io.File
import java.util.*


class IntentHandler : AppCompatActivity() {

    companion object {
        const val REQUEST_PERMISSION = 1
    }

    var ticketNumberBox: TextView? = null

    val SHARED_PREF_FILE = "com.cembas.mobiledevelopertoolsforjira.appdata"
    var preffs: SharedPreferences? = null

    var mContext: Context? = null
    val PICK_IMAGE_REQUEST = 1
    val PICK_FILE_REQUEST = 1
    val PICK_VIDEO_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_handler)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        preffs = this.getSharedPreferences(SHARED_PREF_FILE, 0)

        try {

            val intent = intent as Intent

                val imageUri: Uri = intent.extras["imageUri"] as Uri

                Log.d("URI PATH 1", imageUri.toString())

                val realPath = GetPath.getRealPathFromUri(this, imageUri)

                val intentUri = Uri.parse(realPath)

            val cR = this@IntentHandler.contentResolver

            val type = cR.getType(imageUri)

            Log.d("URI PATH 1", type.toString())

            if (type.startsWith("image/")) {
                handleSendImage(intentUri, realPath)
            } else  if (type.startsWith("video/")) {
                handleSendVideo(intentUri, realPath)
            }

                Log.d("REAL PATH 1", realPath.toString())

            } catch (e: Throwable) {

            val intent = intent
            val bundle = intent.extras
            val imageUri = bundle!!.get(Intent.EXTRA_STREAM) as Uri

            Log.d("URI PATH 1", imageUri.toString())

            val realPath = GetPath.getRealPathFromUri(this, imageUri)

            val intentUri = Uri.parse(realPath)

            val cR = this@IntentHandler.contentResolver

            val type = cR.getType(imageUri)

            Log.d("URI PATH 1", type.toString())

            if (type.startsWith("image/")) {
                handleSendImage(intentUri, realPath)
            } else  if (type.startsWith("video/")) {
                handleSendVideo(intentUri, realPath)
            }

            Log.d("REAL PATH 1", realPath.toString())

        }
    }

    fun handleSendDocument(intentUri: Uri, realPath: String) {
        if (intentUri != null) {
            // Update UI to reflect image being shared
            var ticketNumberBox = findViewById<EditText>(R.id.ticketNumber)

            var imageView: ImageView = findViewById(R.id.imageView)
            imageView.setImageURI(intentUri)

            var sendButton = findViewById<View>(R.id.sendButton)

            sendButton.setOnClickListener {

                val ticketNumber = ticketNumberBox?.text.toString()

                val jiraAddressPreffs = preffs?.getString("JiraAddress", "")

                val credentials = preffs?.getString("credentials", "")

                val baseUrl = "https://"+jiraAddressPreffs

                SendImage().execute(credentials, realPath, baseUrl, ticketNumber)

                Toast.makeText(this@IntentHandler, "Document Sent",
                        Toast.LENGTH_LONG).show()

                Thread.sleep(2000)

                val intent = Intent(this@IntentHandler, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


    fun handleSendText(intent: Intent) {
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
        if (sharedText != null) {
            // Update UI to reflect text being shared
        }
    }

    fun handleSendImage(imageUri: Uri, realPath: String) {
        if (imageUri != null) {
            var ticketNumberBox = findViewById<EditText>(R.id.ticketNumber)

            // Update UI to reflect image being shared
            var imageView: ImageView = findViewById(R.id.imageView)
            imageView.setImageURI(imageUri)

            var sendButton = findViewById<View>(R.id.sendButton)

            sendButton.setOnClickListener {

                val ticketNumber = ticketNumberBox?.text.toString()

                val jiraAddressPreffs = preffs?.getString("JiraAddress", "")

                val credentials = preffs?.getString("credentials", "")

                val baseUrl = "https://"+jiraAddressPreffs

                SendImage().execute(credentials, realPath, baseUrl, ticketNumber)

                Toast.makeText(this@IntentHandler, "Image Sent",
                        Toast.LENGTH_LONG).show()

                Thread.sleep(2000)

                val intent = Intent(this@IntentHandler, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun handleSendMultipleImages(imageUris: Uri) {
        val imageUris = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }

    fun handleSendVideo(videoUri: Uri, realPath: String) {
        var ticketNumberBox = findViewById<EditText>(R.id.ticketNumber)

        if (videoUri != null) {
            var videoView: VideoView = findViewById(R.id.videoView)

            var intentUri = Uri.parse(realPath)

            handleSendImage(intentUri, realPath)

            videoView.setVideoURI(videoUri)
            videoView.start()

            var sendButton = findViewById<View>(R.id.sendButton)

            sendButton.setOnClickListener {

                val ticketNumber = ticketNumberBox?.text.toString()

                val jiraAddressPreffs = preffs?.getString("JiraAddress", "")

                val credentials = preffs?.getString("credentials", "")

                val baseUrl = "https://"+jiraAddressPreffs

                SendImage().execute(credentials, realPath, baseUrl, ticketNumber)

                Toast.makeText(this@IntentHandler, "Video Sent",
                        Toast.LENGTH_LONG).show()

                Thread.sleep(2000)

                val intent = Intent(this@IntentHandler, MainActivity::class.java)
                startActivity(intent)
            }


        }
    }


    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_PERMISSION)
        } else {
            write()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
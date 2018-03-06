package com.cembas.mobiledevelopertoolsforjira

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import com.cembas.mobiledevelopertoolsforjira.R
import java.io.File
import java.util.*



class VideoHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_handler)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var ticketNumberBox = findViewById<EditText>(R.id.ticketNumberVideo)

        var attOnlyButton = findViewById<View>(R.id.addVideo)


        try {

            val intent = intent
            val action = intent.action
            val type = intent.type

            val videoUri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri

            Log.d("URI PATH 1", videoUri.toString())

            val realPath = GetPath.getRealPathFromUri(this, videoUri)



            Log.d("REAL PATH 1", realPath.toString())

            if (Intent.ACTION_SEND.equals(action) && type != null) {
                if ("text/plain" == type) {
                    handleSendText(intent) // Handle text being sent
                } else if (type.startsWith("video/")) {
                    if (videoUri != null) {
                        handleSendVideo(videoUri)
                    }
                    // Handle single image being sent
                }
            } else if (Intent.ACTION_SEND_MULTIPLE == action && type != null) {
                if (type.startsWith("image/")) {
                    handleSendMultipleImages(intent) // Handle multiple images being sent
                }
            } else {
                // Handle other intents, such as being started from the home screen
            }

            attOnlyButton.setOnClickListener {

                val ticketNumber = ticketNumberBox.text.toString()

                SendVideo().execute(realPath, ticketNumber)

                Toast.makeText(this@VideoHandler, "Video Sent 1",
                        Toast.LENGTH_LONG).show()

                Thread.sleep(2000)
            }


        } catch (e: TypeCastException) {

            val imageUri: Uri = intent.extras["imageUri"] as Uri

            Log.d("URI PATH 2", imageUri.toString())

            //val imageUri: Uri = intent.getParcelableExtra<Parcelable>("imageUri") as Uri

            val realPath = GetPath.getRealPathFromUri(this, imageUri)

            Log.d("REAL2", realPath.toString())

            handleSendVideo(imageUri)

            attOnlyButton.setOnClickListener {

                val ticketNumber = ticketNumberBox.text.toString()

                SendVideo().execute(realPath, ticketNumber)

                Toast.makeText(this@VideoHandler, "Video Sent",
                        Toast.LENGTH_LONG).show()

                Thread.sleep(2000)

                val intent = Intent(this@VideoHandler, MainActivity::class.java)
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

    fun handleSendImage(imageUri: Uri) {
        if (imageUri != null) {
            // Update UI to reflect image being shared
            var imageView: ImageView = findViewById(R.id.imageView)
            imageView.setImageURI(imageUri)
        }
    }

    fun handleSendVideo(videoUri: Uri) {
        if (videoUri != null) {
            val videoView = findViewById<VideoView>(R.id.videoView)

            videoView.setVideoURI(videoUri)
            videoView.start()
        }
    }

    fun handleSendMultipleImages(intent: Intent) {
        val imageUris = intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)
        if (imageUris != null) {
            // Update UI to reflect multiple images being shared
        }
    }


    override fun onStart() {
        super.onStart()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), IntentHandler.REQUEST_PERMISSION)
        } else {
            write()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            IntentHandler.REQUEST_PERMISSION -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
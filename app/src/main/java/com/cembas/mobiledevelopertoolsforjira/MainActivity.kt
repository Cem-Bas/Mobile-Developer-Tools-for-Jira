package com.cembas.mobiledevelopertoolsforjira

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.daimajia.androidviewhover.BlurLayout
import android.content.ContentResolver
import android.support.v7.app.ActionBar
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    var mContext: Context? = null
    val PICK_IMAGE_REQUEST = 1
    val PICK_FILE_REQUEST = 1
    val PICK_VIDEO_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        mContext = this

        //check if user already logged in
        // if not show jiraCredRecordActivity
        //if returning user show MainActivity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar?.setCustomView(R.layout.abs_layout);


        var floatingActionButton1 = findViewById<View>(R.id.material_design_floating_action_menu_item1)
        var floatingActionButton2 = findViewById<View>(R.id.material_design_floating_action_menu_item2)

        //research this
        //BlurLayout.setGlobalDefaultDuration(450)

        //LAYOUT 1
        var sendFileBoxLayout: BlurLayout = findViewById(R.id.blurLayoutSendfileBox)
        var hoverSendFile: View = LayoutInflater.from(mContext).inflate(R.layout.hover_send_file, null)

        hoverSendFile.findViewById<View>(R.id.imageIconView).setOnClickListener {
            YoYo.with(Techniques.Hinge)
                    .duration(550)
                    .playOn(hoverSendFile)

            val intent = Intent()
            // Show only images, no videos or anything else
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            // Always show the chooser (if there are multiple options available)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)

        }

        hoverSendFile.findViewById<View>(R.id.videoIconView).setOnClickListener {
            YoYo.with(Techniques.Hinge)
                    .duration(550)
                    .playOn(hoverSendFile)

                    val intent = Intent()
                    // Show only images, no videos or anything else
                    intent.type = "video/*"
                    intent.action = Intent.ACTION_PICK

                    startActivityForResult(Intent.createChooser(intent, "Select Video"), PICK_VIDEO_REQUEST)
        }

        hoverSendFile.findViewById<View>(R.id.documentIconView).setOnClickListener {
            YoYo.with(Techniques.Tada)
                    .duration(550)
                    .playOn(hoverSendFile)

            Toast.makeText(this, "This feature will be added soon.",
                    Toast.LENGTH_LONG).show()


              //       val intent = Intent()
              //       // Show only images, no videos or anything else
              //       intent.type = "*/*"
              //       intent.action = Intent.ACTION_PICK

              //      startActivityForResult(Intent.createChooser(intent, "Select Document"), PICK_FILE_REQUEST)
        }

        sendFileBoxLayout.setHoverView(hoverSendFile)
        sendFileBoxLayout.setBlurDuration(550)
        sendFileBoxLayout.addChildAppearAnimator(hoverSendFile, R.id.imageIconView, Techniques.FlipInX, 550, 250)
        sendFileBoxLayout.addChildAppearAnimator(hoverSendFile, R.id.videoIconView, Techniques.FlipInX, 550, 100)
        sendFileBoxLayout.addChildAppearAnimator(hoverSendFile, R.id.documentIconView, Techniques.FlipInX, 550, 0)


        sendFileBoxLayout.addChildDisappearAnimator(hoverSendFile, R.id.imageIconView, Techniques.FlipOutX, 550, 250)
        sendFileBoxLayout.addChildDisappearAnimator(hoverSendFile, R.id.videoIconView, Techniques.FlipOutX, 550, 100)
        sendFileBoxLayout.addChildDisappearAnimator(hoverSendFile, R.id.documentIconView, Techniques.FlipOutX, 550, 0)

        sendFileBoxLayout.addChildAppearAnimator(hoverSendFile, R.id.descriptionSendFile, Techniques.FadeInUp)
        sendFileBoxLayout.addChildDisappearAnimator(hoverSendFile, R.id.descriptionSendFile, Techniques.FadeOutDown)


        //LAYOUT 2
        var sendCommentLayout: BlurLayout = findViewById(R.id.blur_layout_send_comment)
        var hoverSendComment: View = LayoutInflater.from(mContext).inflate(R.layout.hover_send_comment, null)

        hoverSendComment.findViewById<View>(R.id.commentIconView).setOnClickListener {
            YoYo.with(Techniques.Hinge)
                    .duration(550)
                    .playOn(hoverSendFile)

            // Always show the chooser (if there are multiple options available)
            val intentSendComment = Intent(mContext, TextHandler::class.java)
            startActivity(intentSendComment)
        }

        sendCommentLayout.setHoverView(hoverSendComment)
        sendCommentLayout.setBlurDuration(550)
        sendCommentLayout.addChildAppearAnimator(hoverSendComment, R.id.commentIconView, Techniques.FlipInX, 550, 0)

        sendCommentLayout.addChildDisappearAnimator(hoverSendComment, R.id.commentIconView, Techniques.FlipOutX, 550, 500)

        sendCommentLayout.addChildAppearAnimator(hoverSendComment, R.id.descriptionSendComment, Techniques.FadeInUp)
        sendCommentLayout.addChildDisappearAnimator(hoverSendComment, R.id.descriptionSendComment, Techniques.FadeOutDown)

        //LAYOUT 3
        var viewTicketLayout: BlurLayout = findViewById(R.id.blur_layout_view_ticket)
        var hoverViewTicket: View = LayoutInflater.from(mContext).inflate(R.layout.hover_view_ticket, null)

        hoverViewTicket.findViewById<View>(R.id.ticketIconView).setOnClickListener {
            YoYo.with(Techniques.Hinge)
                    .duration(550)
                    .playOn(hoverViewTicket)
            val intent = Intent(mContext, WebViewActivity::class.java)
            startActivity(intent)

        }

        viewTicketLayout.setHoverView(hoverViewTicket)
        viewTicketLayout.setBlurDuration(550)
        viewTicketLayout.addChildAppearAnimator(hoverViewTicket, R.id.ticketIconView, Techniques.FlipInX, 550, 0)

        viewTicketLayout.addChildDisappearAnimator(hoverViewTicket, R.id.ticketIconView, Techniques.FlipOutX, 550, 500)

        viewTicketLayout.addChildAppearAnimator(hoverViewTicket, R.id.descriptionViewTicket, Techniques.FadeInUp)
        viewTicketLayout.addChildDisappearAnimator(hoverViewTicket, R.id.descriptionViewTicket, Techniques.FadeOutDown)


        floatingActionButton1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //TODO something when floating action menu first item clicked
                val intent = Intent(mContext, JiraCredentials::class.java)
                startActivity(intent)

            }
        })

        floatingActionButton2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //TODO something when floating action menu third item clicked
                val intent = Intent(mContext, About::class.java)
                startActivity(intent)

            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        // return if (id == R.id.action_settings) {
        //     true
        //  } else super.onOptionsItemSelected(item)
        return true
    }

    private val PICK_IMAGE_MULTIPLE = 1


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        val cR = mContext?.contentResolver


        val uri = data?.data as Uri

        Log.d("URI TEST", uri.toString())

        val intent = Intent(this, IntentHandler::class.java)
        intent.putExtra("imageUri", uri)

        val type = cR!!.getType(uri)

        Log.d("URI TYPE TEST", type.toString())

        if (type.startsWith("image/")) {
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
            
        } else  if (type.startsWith("video/")) {
            startActivityForResult(intent, PICK_VIDEO_REQUEST)
        }
    }
}

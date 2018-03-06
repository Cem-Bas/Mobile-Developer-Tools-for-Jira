package com.cembas.mobiledevelopertoolsforjira

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog



class JiraCredentials : AppCompatActivity() {

    val SHARED_PREF_FILE = "com.cembas.mobiledevelopertoolsforjira.appdata"
    var preffs: SharedPreferences? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jira_credentials)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    var jiraAddressEdit: EditText = findViewById(R.id.jiraaddress)

    var usernameEdit: EditText = findViewById(R.id.username)

    var passwordEdit: EditText = findViewById(R.id.password)

    var saveButton: Button = findViewById(R.id.savecred)

        preffs = this.getSharedPreferences(SHARED_PREF_FILE, 0)

        val jiraAddressPreffs = preffs!!.getString("JiraAddress", "")

        if (jiraAddressPreffs == "") {
            MaterialDialog.Builder(this)
                    .title(R.string.first_time_user_title)
                    .content(R.string.first_time_user_dialog)
                    .positiveText("OK")
                    .show()
        } else {
            return
        }


        val jiraAddressSaved: String = preffs!!.getString("JiraAddress", "")

        jiraAddressEdit.setText(jiraAddressSaved)

        val usernameSaved: String = preffs!!.getString("username", "")

        usernameEdit.setText(usernameSaved)

        val passwordSaved: String = preffs!!.getString("password", "")

        passwordEdit.setText(passwordSaved)

        //Save
    saveButton.setOnClickListener {

        val editor = preffs!!.edit()

        editor.putString("JiraAddress", jiraAddressEdit.text.toString())
        editor.putString("username", usernameEdit.text.toString())
        editor.putString("password", passwordEdit.text.toString())

        val token: String = usernameEdit.text.toString()+":"+passwordEdit.text.toString()

        editor.putString("token", token)

        val userCredentialsBase64 = Base64.encodeToString(token.toByteArray(), Base64.NO_WRAP)

        editor.putString("credentials", "Basic "+userCredentialsBase64)

        editor.commit()

        val testText: String = preffs!!.getString("JiraAddress", "jiradevtools.atlassian.net")

        Log.d("JIRA ADDRESS", testText)

        try{
            //sendComment().execute(usernameEdit.text.toString())
        }
        catch (E: Exception){
                E.printStackTrace()
        }

        Toast.makeText(this@JiraCredentials, "Credentials Saved",
                Toast.LENGTH_LONG).show()

        Thread.sleep(2000)

        val intent = Intent(this@JiraCredentials, MainActivity::class.java)
        startActivity(intent)

    }

     //   Toast.makeText(this, "Saved to SharedPreferences - Jira Address: "+token, Toast.LENGTH_LONG).show()
        // TimeUnit.SECONDS.sleep(1)




    }
}

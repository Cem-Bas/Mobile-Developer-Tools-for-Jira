package com.cembas.mobiledevelopertoolsforjira

import android.os.AsyncTask
import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by cem on 11/18/17.
 */
class SendComment : AsyncTask<String, String, String>() {

    override fun doInBackground(vararg params: String): String {

        val credentials = params[0]
        val baseUrl = params[1]
        val ticketNumber = params[2]
        val token = "application/json"

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(CommentService::class.java)

        val comment = JSONObject()
        comment.put("body", params[3])

        val lastComment = "'" + comment.toString() + "'"

        val commentBody = RequestBody.create(MediaType.parse("text/plain"), comment.toString())

        fun makeComment(): Call<Response3> {
            return service.add(credentials, token, ticketNumber, commentBody)
        }

        Log.d("COMMENT LOGS", "BEFORE2")

        val response = makeComment().execute()

        Log.d("COMMENT LOGS", "AFTER2")

        val responseCode = response.code().toString()

        return responseCode}

}
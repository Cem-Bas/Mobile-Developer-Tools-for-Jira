package com.cembas.mobiledevelopertoolsforjira

import android.os.AsyncTask
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by cem on 11/18/17.
 */
class SendVideo : AsyncTask<String, String, String>() {
    override fun doInBackground(vararg params: String): String {

        val credentials = params[0]
        val imagePath = params[1]
        val jiraAddressPreffs = params[2]
        var ticketNumber = params[3]
        val token = "nocheck"


        val file = File(imagePath)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(jiraAddressPreffs)
                .build()

        val service = retrofit.create(VideoService::class.java)

        val map = HashMap<String, MultipartBody.Part>()

        val reqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)


        //for different media types just change the extensions
        val multi = MultipartBody.Part.createFormData("file", "Video.mp4", reqBody)

        map.put("file\"; filename=\"Video.mp4\"", multi)

        Log.d("TEST", "BEFORE")

        val call: Call<List<Response2>> = service.add(credentials, token, ticketNumber, multi)

        val response = call.execute()

        Log.d("RESPONSE", response.raw().toString())

        val responseCode = response.code().toString()

        return responseCode}

}
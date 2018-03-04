package com.cembas.mobiledevelopertoolsforjira

import android.content.SharedPreferences
import android.os.Bundle
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by cem on 11/16/17.
 */

interface ImageService {

    @Multipart
    @POST("rest/api/2/issue/{ticketNumber}/attachments")
    fun add (
            @Header("Authorization") credentials: String,
            @Header("X-Atlassian-Token") token: String,
            @Path(value = "ticketNumber", encoded = true) ticketNumber: String,
            @Part image: MultipartBody.Part)
            : Call<Response2>
}
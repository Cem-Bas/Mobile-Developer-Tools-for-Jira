package com.cembas.mobiledevelopertoolsforjira

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by cem on 11/16/17.
 */

interface CommentService {

    @POST("rest/api/2/issue/{ticketNumber}/comment")
         fun add (
            @Header("Authorization") credentials: String,
            @Header("Content-Type") token: String,
            @Path(value = "ticketNumber", encoded = true) ticketNumber: String,
            @Body comment: RequestBody)
            : Call<Response3>
}

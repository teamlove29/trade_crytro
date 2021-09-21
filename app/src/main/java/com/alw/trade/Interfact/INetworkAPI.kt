package com.alw.trade.Interfact

import com.alw.trade.Modal.DataToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface INetworkAPI {
      @GET("latest")
      fun getDataToken():Call<List<DataToken>>
//    @POST("/qr_login")
//    fun postStationToFront(@Body postStation: PostStation): Call<PostResponseModel>

}
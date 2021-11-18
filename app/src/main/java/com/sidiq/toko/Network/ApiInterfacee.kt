package com.sidiq.toko.Network

import com.sidiq.toko.Model.Login
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterfacee {

   @FormUrlEncoded
    @POST("login/loginTest")
    fun getLogin(

        @Field("username") username: String,
        @Field("password") password: String


    )
            : Call<Login>
}
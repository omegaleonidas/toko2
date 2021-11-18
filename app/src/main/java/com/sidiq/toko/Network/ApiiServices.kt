package com.sidiq.toko.Network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServices {

    companion object {

        val baseUrl = "http://dev.pitjarus.co/api/sariroti_md/index.php/"
        fun restApi(): ApiInterfacee{

            val intercepton = HttpLoggingInterceptor()
            intercepton.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttp = OkHttpClient().newBuilder().addInterceptor(intercepton).build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiInterfacee::class.java)
        }
    }





}
package com.auxilitos.mis_primeros_auxilitos.client

import com.auxilitos.mis_primeros_auxilitos.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getRetrofit(): Retrofit{

        val logger = HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("http://192.168.1.106:8000/")
            .build()

        return retrofit
    }

    fun getApiService(): ApiService{
        return getRetrofit().create(ApiService::class.java)
    }

}
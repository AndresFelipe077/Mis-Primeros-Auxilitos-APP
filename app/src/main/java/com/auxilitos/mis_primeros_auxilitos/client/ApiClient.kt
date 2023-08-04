package com.auxilitos.mis_primeros_auxilitos.client

import com.auxilitos.mis_primeros_auxilitos.service.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getRetrofit(): Retrofit {

        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("http://10.185.208.104:8000/")
            .build()
    }

    fun getApiService(): ApiService{
        return getRetrofit().create(ApiService::class.java)
    }

}
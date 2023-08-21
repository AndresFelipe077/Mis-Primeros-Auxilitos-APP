package com.auxilitos.mis_primeros_auxilitos.service

import com.auxilitos.mis_primeros_auxilitos.model.request.LoginRequest
import com.auxilitos.mis_primeros_auxilitos.model.request.RegisterRequest
import com.auxilitos.mis_primeros_auxilitos.model.response.LoginResponse
import com.auxilitos.mis_primeros_auxilitos.model.response.RegisterResponse
import com.auxilitos.mis_primeros_auxilitos.model.response.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/api/users/{userId}")
    fun getUserProfile(@Path("userId") userId: String): Call<User>

    @POST("/api/login/")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/register/")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("/api/logout/")
    fun logoutUser(): Call<RegisterResponse>?
    //fun getPublicaciones(): Call<List<Publicaciones?>?>?

    @GET("/api/delete/")
    fun deleteUser()


}
package com.auxilitos.mis_primeros_auxilitos.model.response

data class LoginResponse (
    val id:       Int,
    val email:    String,
    val password: String,
)
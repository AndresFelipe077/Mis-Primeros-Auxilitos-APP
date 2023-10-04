package com.auxilitos.mis_primeros_auxilitos.model.response

data class ContenidoResponse(
    val id: Int,
    val title: String,
    val slug: String,
    val url: String,
    val autor: String,
    val description: String,
    val user_id: Int,
    val created_at: String,
    val updated_at: String
)

package com.auxilitos.mis_primeros_auxilitos.model.request

data class ContentRequest(
    val title: String,
    val slug: String?,
    val url: String,
    val autor: String,
    val description: String,
    val user_id: Int
)
package com.example.tmbdmovies.data.models

data class BaseResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)
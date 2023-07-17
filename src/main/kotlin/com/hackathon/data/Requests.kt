package com.hackathon.data

data class ContentRequest(
    val filename: String,
    val base64EncodedImage: String?,
    val language: String
)
package com.hackathon.data

data class ContentRequest(
    val filename: String,
    val base64EncodedImage: String?,
    val language: String
)

data class TranslateDescriptionRequest(
    val description: String,
    val language: String,
    val sourceLanguage: String
)

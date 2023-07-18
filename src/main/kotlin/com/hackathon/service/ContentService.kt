package com.hackathon.service

import com.hackathon.data.ContentRequest
import org.springframework.stereotype.Service

@Service
class ContentService(private val imageDescriptionService: ImageDescriptionService, private val translationService: TranslationService) {

    fun generateAndTranslateDescription(filename: String, language: String): String {
        val baseDescription = imageDescriptionService.generateDescription(filename)
        return translationService.translate(baseDescription, language)
    }

    fun generateAndTranslateDescription(request: ContentRequest): String {
        val baseDescription = imageDescriptionService.generateImageDescription(request.base64EncodedImage!!)
        return translationService.translate(baseDescription, request.language)
    }

}
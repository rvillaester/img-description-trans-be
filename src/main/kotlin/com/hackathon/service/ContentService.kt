package com.hackathon.service

import org.springframework.stereotype.Service

@Service
class ContentService(private val imageDescriptionService: ImageDescriptionService, private val translationService: TranslationService) {

    fun generateAndTranslateDescription(filename: String, language: String): String {
        val baseDescription = imageDescriptionService.generateDescription(filename)
        return translationService.translate(baseDescription, language)
    }

}
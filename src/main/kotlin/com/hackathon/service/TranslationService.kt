package com.hackathon.service

import com.amazonaws.services.translate.AmazonTranslate
import com.amazonaws.services.translate.model.TranslateTextRequest
import com.amazonaws.services.translate.model.TranslateTextResult
import org.springframework.stereotype.Service


@Service
class TranslationService(private val amazonTranslate: AmazonTranslate) {

    fun translate(text: String, targetLanguage: String): String {
        val request: TranslateTextRequest = TranslateTextRequest()
            .withText(text)
            .withSourceLanguageCode("en")
            .withTargetLanguageCode(targetLanguage)
        val result: TranslateTextResult = amazonTranslate.translateText(request)
        return result.translatedText
    }
}
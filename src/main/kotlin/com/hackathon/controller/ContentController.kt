package com.hackathon.controller

import com.hackathon.data.ContentRequest
import com.hackathon.data.TranslateDescriptionRequest
import com.hackathon.service.ContentService
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ComputerVisionErrorResponseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/content")
class ContentController(private val service: ContentService) {

    @PostMapping("upload")
    fun upload(@RequestBody request: ContentRequest): ResponseEntity<String> {
        if (request.base64EncodedImage?.isEmpty()!!) {
            return ResponseEntity("No image provided", org.springframework.http.HttpStatus.BAD_REQUEST)
        }

        try {
            return ResponseEntity.ok(service.generateAndTranslateDescription(request))
        } catch (e: ComputerVisionErrorResponseException) {
            return ResponseEntity("Error describing image", org.springframework.http.HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            return ResponseEntity("Unknown error encountered", org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("translate")
    fun translate(@RequestBody request: TranslateDescriptionRequest): String {
        return service.generateDescriptionTranslation(request)
    }

    @PostMapping("uploadHack")
    fun uploadHack(@RequestBody request: ContentRequest): String {
        return service.generateAndTranslateDescription(request.filename, request.language)
    }

    @PostMapping("test")
    fun test(@RequestParam("text") text: String): String {
        return "$text - Hello there."
    }
}

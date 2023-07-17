package com.hackathon.controller

import com.hackathon.service.ContentService
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/content")
class ContentController(private val service: ContentService) {

    @PostMapping("upload")
    fun upload(@RequestParam("language") language: String, @RequestParam("file") file: MultipartFile): String {
        val filename = file.originalFilename?.let { StringUtils.cleanPath(it) } ?: file.name
        return service.generateAndTranslateDescription(filename, language)
    }

    @PostMapping("uploadHack")
    fun uploadHack(@RequestParam("language") language: String, @RequestParam("file") file: String): String {
        return service.generateAndTranslateDescription(file, language)
    }

    @PostMapping("test")
    fun test(@RequestParam("text") text: String): String {
        return "$text - Hello there."
    }
}
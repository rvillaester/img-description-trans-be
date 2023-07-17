package com.hackathon.service

import org.springframework.stereotype.Service

@Service
class ImageDescriptionService {

    fun generateDescription(filename: String): String {
        return descriptionMap[filename] ?: ""
    }

    companion object {
        val descriptionMap = mapOf(
            "a.png" to "description of A",
            "b.png" to "description of B",
            "c.png" to "description of C",
            "d.png" to "description of D",
            "e.png" to "description of E",
        )
    }

}
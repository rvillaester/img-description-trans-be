package com.hackathon.service

import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient
import org.springframework.stereotype.Service
import javax.xml.bind.DatatypeConverter


@Service
class ImageDescriptionService(private var computeVisionClient: ComputerVisionClient) {

    fun generateDescription(filename: String): String {
        return descriptionMap[filename] ?: ""
    }

    fun generateImageDescription(base64EncodedImage: String): String {
        var base64 = base64EncodedImage
        if (base64.contains(",")) {
            base64 = base64.split(",")[1]
        }

        val imageBytes: ByteArray = DatatypeConverter.parseBase64Binary(base64)

        val analysis = computeVisionClient.computerVision()
            .describeImageInStream()
            .withImage(imageBytes)
            .execute()

        if (analysis?.captions()?.size!! > 0) {
            return analysis.captions().first().text()
        }

        return "No description found"
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
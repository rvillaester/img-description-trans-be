package com.hackathon.service

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import javax.xml.bind.DatatypeConverter


@Service
class ImageDescriptionService(private val ssm: AWSSimpleSystemsManagement) {

    fun generateDescription(filename: String): String {
        return descriptionMap[filename] ?: ""
    }

    fun generateImageDescription(base64EncodedImage: String): String {
        val imageBytes: ByteArray = DatatypeConverter.parseBase64Binary(base64EncodedImage)
        val img = ImageIO.read(ByteArrayInputStream(imageBytes))
        val apiKey = getSecret("/hackathon/api-key")

        // TODO: call actual API that will generate the image description
        return "This is to be implemented"
    }

    private fun getSecret(paramName: String): String {
        val paramRequest: GetParameterRequest = GetParameterRequest()
            .withName(paramName)
        return ssm.getParameter(paramRequest).parameter.value
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
package com.hackathon;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper(final Jackson2ObjectMapperBuilder builder) {
        return builder
            .build()
            .registerModule(new JavaTimeModule())
            .setDefaultLeniency(false);
    }

    @Bean
    public AmazonTranslate amazonTranslate() {
        return AmazonTranslateClient.builder()
            .withRegion(Regions.AP_SOUTHEAST_2)
            .build();
    }

    @Bean
    public AWSSimpleSystemsManagement ssm() {
        return AWSSimpleSystemsManagementClientBuilder
            .standard().withRegion(Regions.AP_SOUTHEAST_2).build();
    }

    @Bean
    public ComputerVisionClient computerVisionClient(AWSSimpleSystemsManagement ssm) {
        return ComputerVisionManager
                .authenticate(getSecret(ssm))
                .withEndpoint("https://rick-vision-test.cognitiveservices.azure.com/");
    }

    private String getSecret(AWSSimpleSystemsManagement ssm) {
        GetParameterRequest paramRequest = new GetParameterRequest()
                .withName("/hackathon/api-key");
        return ssm.getParameter(paramRequest).getParameter().getValue();
    }
}

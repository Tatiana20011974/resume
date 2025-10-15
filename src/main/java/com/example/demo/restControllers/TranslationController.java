/*
package com.example.demo.restControllers;

import com.example.translator.TranslatorProperties;
import com.example.translator.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/translation")
@RequiredArgsConstructor
public class TranslationController {
    RestTemplate restTemplate;
    @GetMapping
    public ResponseEntity<String>translate(@RequestParam String text) {
        TranslatorProperties properties = new TranslatorProperties();
        properties.setLanguageFrom("EN");
        properties.setLanguageTo("RU");
        TranslationService service = new TranslationService(properties, restTemplate);
        String translation = service.translate(text);
        return ResponseEntity.ok(translation);
    }
}*/

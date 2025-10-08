package com.example.demo.restControllers;

import com.example.demo.request.UserRequest;
import com.example.demo.response.AuthResponse;
import com.example.demo.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final ObjectMapper objectMapper;
    private final AuthService service;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody UserRequest user){
        ResponseEntity<String> response = service.sendTokenRequest(user.getLogin(), user.getPassword());
        if (response.getStatusCode().is2xxSuccessful()){
            try {
                AuthResponse authResponse = objectMapper.readValue(response.getBody(), AuthResponse.class);
                return ResponseEntity.ok(authResponse);
            } catch (JsonProcessingException e){
                throw new RuntimeException("", e);
            }
        } else {
            throw new RuntimeException("" + response.getStatusCode());
        }
    }
}

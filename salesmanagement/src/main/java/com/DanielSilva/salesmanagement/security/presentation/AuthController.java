package com.DanielSilva.salesmanagement.security.presentation;

import com.DanielSilva.salesmanagement.security.application.dto.AuthResponse;
import com.DanielSilva.salesmanagement.security.application.form.LoginRequest;
import com.DanielSilva.salesmanagement.security.application.service.AuthService;
import com.DanielSilva.salesmanagement.security.application.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest form) {
        return ResponseEntity.ok(authService.authenticate(form));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestParam String refreshToken) {
        AuthResponse AuthResponse = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(AuthResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decode-jwt")
    public ResponseEntity<Map<String, Object>> decodeJwtPayload(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        Map<String, Object> jwtPayload = tokenService.decodeJwtPayload(token);
        return ResponseEntity.ok(jwtPayload);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        Boolean bool = tokenService.validateToken(token);
        return ResponseEntity.ok(bool);
    }
}

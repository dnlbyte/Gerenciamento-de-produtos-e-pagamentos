package com.DanielSilva.salesmanagement.security.presentation;

import com.DanielSilva.salesmanagement.security.application.dto.UserResponse;
import com.DanielSilva.salesmanagement.security.application.form.AuthRequest;
import com.DanielSilva.salesmanagement.security.application.service.AuthService;
import com.DanielSilva.salesmanagement.security.application.service.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> dto = userService.getAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthRequest form) {
        userService.register(form);
        return ResponseEntity.ok().build();
    }
}
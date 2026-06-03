package com.pgi.pgic.controller;

import com.pgi.pgic.dto.LoginRequest;
import com.pgi.pgic.dto.LoginResponse;
import com.pgi.pgic.dto.RegisterRequest;
import com.pgi.pgic.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.pgi.pgic.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.pgi.pgic.dto.ChangePasswordRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client créé avec succès");
    }
        @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(
            @RequestBody @Valid ChangePasswordRequest request,
            @AuthenticationPrincipal UserPrincipal currentUser) {
        authService.changePassword(currentUser.getId(), request);
        return ResponseEntity.ok("Mot de passe modifié avec succès");
    }
}
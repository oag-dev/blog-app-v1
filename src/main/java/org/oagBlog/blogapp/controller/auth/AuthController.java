package org.oagBlog.blogapp.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.oagBlog.blogapp.model.dto.login.LoginDto;
import org.oagBlog.blogapp.model.dto.register.RegisterDto;
import org.oagBlog.blogapp.model.response.ApiResponse;
import org.oagBlog.blogapp.service.auth.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid LoginDto loginDto) {
        ApiResponse apiResponse = authService.loginUser(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/verify_email")
    public ResponseEntity verifyEmail(@RequestParam String emailCode, String email) {
        ApiResponse apiResponse = authService.verifyEmail(emailCode, email);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}

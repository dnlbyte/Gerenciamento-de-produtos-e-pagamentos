package com.DanielSilva.salesmanagement.security.application.service;

import com.DanielSilva.salesmanagement.security.application.dto.AuthResponse;
import com.DanielSilva.salesmanagement.security.application.dto.UserInfoResponse;
import com.DanielSilva.salesmanagement.security.application.form.AuthRequest;
import com.DanielSilva.salesmanagement.security.application.form.LoginRequest;
import com.DanielSilva.salesmanagement.security.domain.exceptions.user.UserNotFoundException;
import com.DanielSilva.salesmanagement.security.domain.model.UserModel;
import com.DanielSilva.salesmanagement.security.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }



    public AuthResponse authenticate(LoginRequest form) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getEmail(), form.getPassword())
        );
        UserModel userModel = userService.findByUsernameAndActiveIsTrue(form.getEmail());
        UserInfoResponse userInfoDto = new UserInfoResponse(userModel.getUsername(), userModel.getRole());

        String accessToken = tokenService.generateAccessToken(userInfoDto);
        String refreshToken = tokenService.generateRefreshToken(userInfoDto);
        return new AuthResponse(accessToken, refreshToken);
    }


    public AuthResponse refreshToken(String refreshToken) {
        String accessToken = tokenService.refreshAccessToken(refreshToken);
        return new AuthResponse(accessToken, refreshToken);
    }

    public void logout(String refreshToken) {
        tokenService.revokeRefreshToken(refreshToken);
    }
}

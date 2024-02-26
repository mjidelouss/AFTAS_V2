package com.example.aftas.service;

import com.example.aftas.VM.request.RefreshTokenRequest;
import com.example.aftas.VM.response.RefreshTokenResponse;
import com.example.aftas.entities.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
    String getRefreshTokenFromLocalStorage(HttpServletRequest request);
    void deleteByToken(String token);
}
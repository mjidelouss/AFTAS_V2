package com.example.aftas.service;


import com.example.aftas.VM.request.AuthenticationRequest;
import com.example.aftas.VM.request.RegisterRequest;
import com.example.aftas.VM.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}

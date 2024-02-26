package com.example.aftas.service.Impl;

import com.example.aftas.VM.request.AuthenticationRequest;
import com.example.aftas.VM.request.RegisterRequest;
import com.example.aftas.VM.response.AuthenticationResponse;
import com.example.aftas.entities.AppUser;
import com.example.aftas.entities.Member;
import com.example.aftas.enums.TokenType;
import com.example.aftas.repository.AppUserRepository;
import com.example.aftas.service.AuthenticationService;
import com.example.aftas.service.JwtService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.example.aftas.mappers.MemberMapper.generateUniqueMembershipNumber;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AppUserRepository userRepository;
    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = AppUser.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        Member member = Member.builder()
                .name(request.getFirstname())
                .familyName(request.getLastname())
                .nationality(request.getNationality())
                .identityNumber(request.getIdentityNumber())
                .identityDocumentType(request.getIdentityDocumentType())
                .accessDate(LocalDate.now())
                .membershipNumber(generateUniqueMembershipNumber())
                .active(false)
                .build();
        user = userRepository.save(user);
        memberService.addMember(member);
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .roles(roles)
                .email(user.getEmail())
                .id(user.getId())
                .refreshToken(refreshToken.getToken())
                .tokenType( TokenType.BEARER.name())
                .build();
    }
}
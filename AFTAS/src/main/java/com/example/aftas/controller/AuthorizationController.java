package com.example.aftas.controller;

import com.example.aftas.entities.AppUser;
import com.example.aftas.enums.Role;
import com.example.aftas.repository.AppUserRepository;
import com.example.aftas.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AppUserRepository userRepository;

    @PostMapping("/activate")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('MANAGER')")
    public ResponseEntity<String> activateAccount(@PathVariable Long id) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseMessage.notFound("User Not Found");
        } else {
            user.setRole(Role.MEMBER);
            return ResponseEntity.ok("Activated Account of User with ID: " + user.getId() + " Successfully");
        }
    }

}
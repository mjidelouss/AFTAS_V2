package com.example.aftas.controller;

import com.example.aftas.entities.AppUser;
import com.example.aftas.entities.Member;
import com.example.aftas.enums.Role;
import com.example.aftas.repository.AppUserRepository;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {

    private final MemberService memberService;
    private final AppUserRepository userRepository;

    @PostMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('MANAGER')")
    public ResponseEntity activateAccount(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        AppUser user = userRepository.findByFirstNameAndLastName(member.getName(), member.getFamilyName());
        if (user == null) {
            return ResponseMessage.notFound("User Not Found");
        } else {
            user.setRole(Role.MEMBER);
            member.setActive(true);
            memberService.addMember(member);
            userRepository.save(user);
            return ResponseMessage.ok("Activated Account of User with ID: " + user.getId() + " Successfully", user);
        }
    }


}
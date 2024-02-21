package com.example.aftas.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.aftas.enums.Privilege.*;

@RequiredArgsConstructor
public enum Role {
    MANAGER(
            Set.of(READ_PRIVILEGE,WRITE_PRIVILEGE,UPDATE_PRIVILEGE,DELETE_PRIVILEGE)
    ),
    JURY(Set.of(READ_PRIVILEGE,WRITE_PRIVILEGE,UPDATE_PRIVILEGE,DELETE_PRIVILEGE)),
    MEMBER(
            Set.of(READ_PRIVILEGE)
    );

    @Getter
    private final Set<Privilege> privileges;

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
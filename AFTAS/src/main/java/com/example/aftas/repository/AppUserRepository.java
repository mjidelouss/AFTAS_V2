package com.example.aftas.repository;

import com.example.aftas.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    @Query("SELECT u FROM AppUser u WHERE u.firstname LIKE %:firstName% OR u.lastname LIKE %:lastName%")
    AppUser findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}

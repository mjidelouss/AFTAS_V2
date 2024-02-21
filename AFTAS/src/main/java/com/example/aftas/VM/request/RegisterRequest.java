package com.example.aftas.VM.request;

import com.example.aftas.Validation.StrongPassword;
import com.example.aftas.enums.IdentityDocumentType;
import com.example.aftas.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "firstname is required")
    private String firstname;
    @NotBlank(message = "lastname is required")
    private String lastname;
    @NotBlank(message = "email is required")
    @Email(message = "email format is not valid")
    private String email;
    @NotBlank(message = "password is required")
    @StrongPassword
    private String password;
    @NotBlank(message = "Nationality Cannot be Blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must only contain Letters")
    private String nationality;
    @NotNull(message = "Identity Document Type Cannot be Null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @NotBlank(message = "Identity Number Cannot be Blank")
    private String identityNumber;
}
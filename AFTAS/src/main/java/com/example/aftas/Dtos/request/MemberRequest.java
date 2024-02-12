package com.example.aftas.Dtos.request;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MemberRequest {
    @NotNull(message = "Name must not be Null")
    @NotBlank(message = "Name Cannot Be Blank")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 Characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String name;

    @NotNull(message = "FamilyName must not be Null")
    @NotBlank(message = "Family name cannot be Blank")
    @Size(min = 3, max = 40, message = "FamilyName must be between 3 and 40 Characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Family name must only contain letters")
    private String familyName;

    @NotNull(message = "Nationality must not be Null")
    @NotBlank(message = "Nationality Cannot be Blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Nationality must only contain Letters")
    private String nationality;

    @NotNull(message = "Identity Document Type Cannot be Null")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity Number must not be Null")
    @NotBlank(message = "Identity Number Cannot be Blank")
    @Column(unique = true)
    @Size(min = 3, max =40, message = "Identity Number must be Between 3 and 4O Characters")
    private String identityNumber;
}

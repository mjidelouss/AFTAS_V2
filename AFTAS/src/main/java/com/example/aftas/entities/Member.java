package com.example.aftas.entities;

import com.example.aftas.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "Membership Number must not be Null")
    @Positive(message = "Membership Number Must Be Positive")
    private Integer membershipNumber;

    @NotNull(message = "Access Date must not be Null")
    @PastOrPresent(message = "Access Date must be in the past or the present")
    @Temporal(TemporalType.DATE)
    private LocalDate accessDate;

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
    @Size(min = 3, max =40, message = "Identity Number must be Between 7 and 20 Characters")
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @Column(nullable = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Rank> ranks;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    @ToString.Exclude
    private List<Hunt> hunts;

    public Member(String name, String familyName, Integer membershipNumber, LocalDate accessDate, String nationality, IdentityDocumentType identityDocumentType, String identityNumber) {
        this.name = name;
        this.familyName = familyName;
        this.membershipNumber = membershipNumber;
        this.accessDate = accessDate;
        this.nationality = nationality;
        this.identityDocumentType = identityDocumentType;
        this.identityNumber = identityNumber;
    }
}

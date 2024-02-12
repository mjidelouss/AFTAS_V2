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

    private String name;

    private String familyName;

    private Integer membershipNumber;

    private LocalDate accessDate;

    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

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

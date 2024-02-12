package com.example.aftas.entities;

import com.example.aftas.enums.CompetitionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Code Cannot be Blank")
    private String code;

    @Column(unique = true)
    @NotNull(message = "Date Cannot Be Null")
    private LocalDate date;

    @NotNull(message = "Start Time Cannot be Null")
    private LocalTime startTime;

    @NotNull(message = "End Time Cannot be Null")
    private LocalTime endTime;

    @NotNull(message = "Total of Participants Cannot be Null")
    @Positive(message = "Total of Participants Must Be Positive")
    private Integer totalParticipants;

    @NotNull(message = "Location Cannot be Null")
    @NotBlank(message = "Location Cannot be Blank")
    private String location;

    @NotNull(message = "Amount Cannot be Null")
    @Positive(message = "Amount Must Be Positive")
    private Double amount;

    @NotNull(message = "Status Cannot be Null")
    @Enumerated(EnumType.STRING)
    private CompetitionStatus status;

    @OneToMany(mappedBy = "competition")
    @Column(nullable = true)
    @JsonIgnore
    @ToString.Exclude
    private List<Rank> ranks;

    public Competition(String code, LocalDate date, LocalTime startTime, LocalTime endTime, Integer totalParticipants, String location, Double amount, CompetitionStatus status) {
        this.code = code;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalParticipants = totalParticipants;
        this.location = location;
        this.amount = amount;
        this.status = status;
    }
}

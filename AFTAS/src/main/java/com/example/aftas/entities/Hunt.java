package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Hunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Number Of Fish Cannot Be Null")
    @PositiveOrZero(message = "Number Of Fish Must Be Positive")
    private Integer numberOfFish;

    @ManyToOne
    @NotNull(message = "Member Cannot Be Null")
    private Member member;

    @ManyToOne
    @NotNull(message = "Fish Cannot Be Null")
    private Fish fish;

    @ManyToOne
    @NotNull(message = "Competition Cannot Be Null")
    private Competition competition;
}

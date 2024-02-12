package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name must not be Null")
    @NotBlank(message = "Name Cannot Be Null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String name;

    @NotNull(message = "AverageWeight must not be Null")
    @Positive(message = "Average weight must be positive")
    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    @JsonIgnore
    @ToString.Exclude
    private List<Hunt> hunts;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "level_id")
    @NotNull(message = "Level Cannot Be Null")
    private Level level;

    public Fish(String name, Double averageWeight, Level level) {
        this.name = name;
        this.averageWeight = averageWeight;
        this.level = level;
    }
}

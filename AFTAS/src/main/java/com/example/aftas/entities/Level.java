package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer level;

    @NotNull(message = "Description Cannot Be Null")
    @NotBlank(message = "Description Cannot Be Blank")
    private String description;

    @Column(unique = true)
    @Positive(message = "Points Must Be Positive")
    private Integer points;

    @OneToMany(mappedBy = "level")
    @JsonIgnore
    @ToString.Exclude
    private List<Fish> fishList;

    public Level(Integer level, String description, Integer points) {
        this.level = level;
        this.description = description;
        this.points = points;
    }
}

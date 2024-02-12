package com.example.aftas.Dtos.request;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class RankRequest {
    @Positive(message = "Rank must be Positive")
    @NotNull(message = "Rank cannot be Null")
    private Integer rank;

    @PositiveOrZero(message = "Score must be Positive")
    @NotNull(message = "Score cannot be Null")
    private Integer score;

    @NotNull(message = "Member cannot be Null")
    private Member member;

    @NotNull(message = "Competition cannot be Null")
    private Competition competition;
}

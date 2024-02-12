package com.example.aftas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "competition_id")
    private Long competitionId;
}

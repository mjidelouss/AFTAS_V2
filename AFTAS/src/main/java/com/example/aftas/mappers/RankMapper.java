package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.RankRequest;
import com.example.aftas.entities.Rank;
import com.example.aftas.entities.RankId;

public class RankMapper {

    public static Rank mapRankRequestToRank(RankRequest rankRequest) {
        return  Rank.builder()
                .id(RankId.builder()
                        .competitionId(rankRequest.getCompetition().getId())
                        .memberId(rankRequest.getMember().getId())
                    .build())
                .rank(rankRequest.getRank())
                .score(rankRequest.getScore())
                .member(rankRequest.getMember())
                .competition(rankRequest.getCompetition())
                .build();
    }
}

package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.RankRequest;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.RankId;

public class RankMapper {

    public static Ranking mapRankRequestToRank(RankRequest rankRequest) {
        return  Ranking.builder()
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

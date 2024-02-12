package com.example.aftas.service;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Rank;

import java.util.List;

public interface RankingService {

    Rank addRanking(Rank rank);
    List<Rank> getRankings();
    void updateScore(Member member, Competition competition, Fish fish);
    Boolean registerMemberToCompetition(Member member, Competition competition);

    List<Rank> getPodium(Competition competition);

    void updateRankingOrder(Competition competition);
}

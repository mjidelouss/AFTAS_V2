package com.example.aftas.service;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;

import java.util.List;

public interface RankingService {

    Ranking addRanking(Ranking ranking);
    List<Ranking> getRankings();
    void updateScore(Member member, Competition competition, Fish fish);
    Boolean registerMemberToCompetition(Member member, Competition competition);

    List<Ranking> getPodium(Competition competition);

    void updateRankingOrder(Competition competition);
}

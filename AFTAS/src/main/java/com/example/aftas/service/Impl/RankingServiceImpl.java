package com.example.aftas.service.Impl;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.enums.CompetitionStatus;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;

    @Override
    public Ranking addRanking(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public List<Ranking> getRankings() {
        return rankingRepository.findAll();
    }

    public void updateScore(Member member, Competition competition, Fish fish) {
        Ranking ranking = rankingRepository.findByMemberAndCompetition(member, competition);
        if (ranking == null) {
            ranking = new Ranking();
            ranking.setMember(member);
            ranking.setCompetition(competition);
            ranking.setScore(0);
            ranking.setRank(0);
        }
        int newScore = ranking.getScore() + fish.getLevel().getPoints();
        ranking.setScore(newScore);
        addRanking(ranking);
    }

    public void updateRankingOrder(Competition competition) {
        List<Ranking> rankings = rankingRepository.findByCompetitionOrderByScoreDesc(competition);

        for (int i = 0; i < rankings.size(); i++) {
            Ranking ranking = rankings.get(i);
            ranking.setRank(i + 1);
            rankingRepository.save(ranking);
        }
    }

    public List<Ranking> getPodium(Competition competition) {
        competition.setStatus(CompetitionStatus.CLOSED);
        competitionRepository.save(competition);
        return rankingRepository.findTop3ByCompetitionOrderByRank(competition);
    }

    public Boolean registerMemberToCompetition(Member member, Competition competition) {
        Ranking existingRanking = rankingRepository.findByMemberAndCompetition(member, competition);
        if (existingRanking == null) {
            Ranking newRanking = new Ranking();
            newRanking.setMember(member);
            newRanking.setCompetition(competition);
            newRanking.setScore(0);
            newRanking.setRank(0);
            rankingRepository.save(newRanking);
            return true;
        } else {
            throw new IllegalStateException("Member is already registered for the competition");
        }
    }

}

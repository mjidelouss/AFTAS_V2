package com.example.aftas.service.Impl;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Rank;
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
    public Rank addRanking(Rank rank) {
        return rankingRepository.save(rank);
    }

    @Override
    public List<Rank> getRankings() {
        return rankingRepository.findAll();
    }

    public void updateScore(Member member, Competition competition, Fish fish) {
        Rank rank = rankingRepository.findByMemberAndCompetition(member, competition);
        if (rank == null) {
            rank = new Rank();
            rank.setMember(member);
            rank.setCompetition(competition);
            rank.setScore(0);
            rank.setRank(0);
        }
        int newScore = rank.getScore() + fish.getLevel().getPoints();
        rank.setScore(newScore);
        addRanking(rank);
    }

    public void updateRankingOrder(Competition competition) {
        List<Rank> ranks = rankingRepository.findByCompetitionOrderByScoreDesc(competition);

        for (int i = 0; i < ranks.size(); i++) {
            Rank rank = ranks.get(i);
            rank.setRank(i + 1);
            rankingRepository.save(rank);
        }
    }

    public List<Rank> getPodium(Competition competition) {
        competition.setStatus(CompetitionStatus.CLOSED);
        competitionRepository.save(competition);
        return rankingRepository.findTop3ByCompetitionOrderByRank(competition);
    }

    public Boolean registerMemberToCompetition(Member member, Competition competition) {
        Rank existingRank = rankingRepository.findByMemberAndCompetition(member, competition);
        if (existingRank == null) {
            Rank newRank = new Rank();
            newRank.setMember(member);
            newRank.setCompetition(competition);
            newRank.setScore(0);
            newRank.setRank(0);
            rankingRepository.save(newRank);
            return true;
        } else {
            throw new IllegalStateException("Member is already registered for the competition");
        }
    }

}

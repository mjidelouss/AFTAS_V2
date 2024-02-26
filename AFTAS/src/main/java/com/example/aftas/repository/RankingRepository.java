package com.example.aftas.repository;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.entities.RankId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankId> {

    Ranking findByMemberAndCompetition(Member member, Competition competition);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);

    List<Ranking> findTop3ByCompetitionOrderByRank(Competition competition);
}

package com.example.aftas.service;

import com.example.aftas.entities.Competition;
import com.example.aftas.enums.CompetitionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {

    Competition addCompetition(Competition competition);

    Competition updateCompetition(Competition competition, Long id);

    Competition getCompetitionById(Long id);

    void deleteCompetition(Long id);

    Page<Competition> getCompetitions(Pageable pageable);

    Competition getCompetitionByCode(String competitionCode);

    List<Competition> getCompetitionsByStatus(CompetitionStatus status);
}

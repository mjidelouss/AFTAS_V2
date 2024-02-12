package com.example.aftas.service.Impl;

import com.example.aftas.entities.Competition;
import com.example.aftas.enums.CompetitionStatus;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Override
    public Competition addCompetition(Competition competition) {
        competition.setCode(generateCompetitionCode(competition));
        validateCompetitionDate(competition.getDate());
        validateCompetitionTime(competition.getStartTime(), competition.getEndTime());
        return competitionRepository.save(competition);
    }

    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        Competition existingCompetition = getCompetitionById(id);
        existingCompetition.setAmount(competition.getAmount());
        existingCompetition.setCode(competition.getCode());
        existingCompetition.setDate(competition.getDate());
        existingCompetition.setLocation(competition.getLocation());
        existingCompetition.setStartTime(competition.getStartTime());
        existingCompetition.setEndTime(competition.getEndTime());
        existingCompetition.setTotalParticipants(competition.getTotalParticipants());
        return competitionRepository.save(existingCompetition);
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public Page<Competition> getCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    @Override
    public List<Competition> getCompetitionsByStatus(CompetitionStatus status) {
        return competitionRepository.findByStatus(status);
    }

    @Override
    public Competition getCompetitionByCode(String competitionCode) {
        return competitionRepository.findByCode(competitionCode);
    }

    public String generateCompetitionCode(Competition competition) {
        String locationCode = competition.getLocation().substring(0, 3).toLowerCase();
        String dateCode = competition.getDate().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        return locationCode + "-" + dateCode;
    }

    public void validateCompetitionDate(LocalDate competitionDate) {
        LocalDate today = LocalDate.now();
        Duration timeUntilCompetition = Duration.between(today.atStartOfDay(), competitionDate.atStartOfDay());

        if (timeUntilCompetition.isNegative() || timeUntilCompetition.toHours() < 48) {
            throw new IllegalArgumentException("Competition date must be at least 48 hours from now");
        }
        List<Competition> existingCompetitionsOnDate = competitionRepository.findByDate(competitionDate);
        if (!existingCompetitionsOnDate.isEmpty()) {
            throw new IllegalArgumentException("Another competition already exists on the same date");
        }
    }
    public void validateCompetitionTime(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before the end time");
        }
    }

}

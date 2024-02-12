package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.CompetitionRequest;
import com.example.aftas.entities.Competition;
import com.example.aftas.enums.CompetitionStatus;

public class CompetitionMapper {

    public static Competition mapCompetitionRequestToCompetition(CompetitionRequest competitionRequest) {
        return Competition.builder()
                .amount(competitionRequest.getAmount())
                .date(competitionRequest.getDate())
                .code(competitionRequest.getCode())
                .startTime(competitionRequest.getStartTime())
                .endTime(competitionRequest.getEndTime())
                .location(competitionRequest.getLocation())
                .totalParticipants(competitionRequest.getTotalParticipants())
                .status(CompetitionStatus.IN_PROGRESS)
                .build();
    }
}

package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.HuntingRequest;
import com.example.aftas.entities.Hunt;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.FishService;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HuntMapper {

    private final FishService fishService;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    public Hunt mapHuntRequestToHunt(HuntingRequest huntingRequest) {
        return Hunt.builder()
                .numberOfFish(huntingRequest.getNumberOfFish())
                .fish(fishService.getFishByName(huntingRequest.getFishName()))
                .member(memberService.getMemberByMembershipNumber(huntingRequest.getMembershipNumber()))
                .competition(competitionService.getCompetitionByCode(huntingRequest.getCompetitionCode()))
                .build();
    }
}

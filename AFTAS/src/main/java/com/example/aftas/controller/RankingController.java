package com.example.aftas.controller;
import com.example.aftas.Dtos.request.PodiumRequest;
import com.example.aftas.Dtos.request.RankRequest;
import com.example.aftas.Dtos.request.RegisterRequest;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.mappers.RankMapper;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;
    private final CompetitionService competitionService;
    private final MemberService memberService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY') or hasRole('MEMBER'))")
    public ResponseEntity getRankings() {
        List<Ranking> rankings = rankingService.getRankings();
        if (rankings.isEmpty()) {
            return ResponseMessage.notFound("Rankings Not Found");
        } else {
            return ResponseMessage.ok("Success", rankings);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY'))")
    public ResponseEntity addRanking(@RequestBody @Valid RankRequest rankRequest) {
        Ranking ranking = RankMapper.mapRankRequestToRank(rankRequest);
        Ranking ranking1 = rankingService.addRanking(ranking);
        if(ranking1 == null) {
            return ResponseMessage.badRequest("Failed To Create Ranking");
        } else {
            return ResponseMessage.created("Ranking Created Successfully", ranking1);
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY'))")
    public ResponseEntity registerMemberToComeptition(@RequestBody @Valid RegisterRequest registerRequest) {
        Competition competition = competitionService.getCompetitionByCode(registerRequest.getCompetitionCode());
        Member member = memberService.getMemberByMembershipNumber(registerRequest.getMembershipNumber());
        if(competition == null) {
            return ResponseMessage.badRequest("Competition Not Found");
        }
        if (member == null) {
            return ResponseMessage.badRequest("Member Not Found");
        }
        Boolean bool = rankingService.registerMemberToCompetition(member, competition);
        if (bool) {
            return ResponseMessage.created("Member Register Successfully", member);
        } else {
            return ResponseMessage.badRequest("Failed To Register Member");
        }
    }

    @PostMapping("/podium")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY') or hasRole('MEMBER'))")
    public ResponseEntity getPodium(@RequestBody @Valid PodiumRequest podiumRequest) {
        Competition competition = competitionService.getCompetitionByCode(podiumRequest.getCode());
        rankingService.updateRankingOrder(competition);
        List<Ranking> rankings = rankingService.getPodium(competition);
        if (rankings.isEmpty()) {
            return ResponseMessage.notFound("Ranks Not Found");
        } else {
            return ResponseMessage.ok("Top 3 Fetched Successfully", rankings);
        }
    }
}

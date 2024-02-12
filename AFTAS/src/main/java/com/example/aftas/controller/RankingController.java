package com.example.aftas.controller;
import com.example.aftas.Dtos.request.PodiumRequest;
import com.example.aftas.Dtos.request.RankRequest;
import com.example.aftas.Dtos.request.RegisterRequest;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Rank;
import com.example.aftas.mappers.RankMapper;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getRankings() {
        List<Rank> ranks = rankingService.getRankings();
        if (ranks.isEmpty()) {
            return ResponseMessage.notFound("Rankings Not Found");
        } else {
            return ResponseMessage.ok("Success", ranks);
        }
    }

    @PostMapping("")
    public ResponseEntity addRanking(@RequestBody @Valid RankRequest rankRequest) {
        Rank rank = RankMapper.mapRankRequestToRank(rankRequest);
        Rank rank1 = rankingService.addRanking(rank);
        if(rank1 == null) {
            return ResponseMessage.badRequest("Failed To Create Ranking");
        } else {
            return ResponseMessage.created("Ranking Created Successfully", rank1);
        }
    }

    @PostMapping("/register")
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
    public ResponseEntity getPodium(@RequestBody @Valid PodiumRequest podiumRequest) {
        Competition competition = competitionService.getCompetitionByCode(podiumRequest.getCode());
        rankingService.updateRankingOrder(competition);
        List<Rank> ranks = rankingService.getPodium(competition);
        if (ranks.isEmpty()) {
            return ResponseMessage.notFound("Ranks Not Found");
        } else {
            return ResponseMessage.ok("Top 3 Fetched Successfully", ranks);
        }
    }
}

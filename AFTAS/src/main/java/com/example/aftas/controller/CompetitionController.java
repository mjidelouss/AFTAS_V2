package com.example.aftas.controller;

import com.example.aftas.Dtos.request.CompetitionRequest;
import com.example.aftas.entities.Competition;
import com.example.aftas.enums.CompetitionStatus;
import com.example.aftas.mappers.CompetitionMapper;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY') or hasRole('MEMBER'))")
    public ResponseEntity getCompetitions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Competition> competitionsPage = competitionService.getCompetitions(PageRequest.of(page, size));

        if (competitionsPage.isEmpty()) {
            return ResponseMessage.notFound("Competitions Not Found");
        } else {
            return ResponseMessage.ok("Success", competitionsPage.getContent());
        }
    }

    @GetMapping("/byStatus/{status}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY') or hasRole('MEMBER'))")
    public ResponseEntity getCompetitionsByStatus(@PathVariable CompetitionStatus status) {
        List<Competition> competitions = competitionService.getCompetitionsByStatus(status);
        return ResponseMessage.ok("Successfully Got Competitions", competitions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY') or hasRole('MEMBER'))")
    public ResponseEntity getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseMessage.notFound("Competition Not Found");
        } else {
            return ResponseMessage.ok("Success", competition);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY'))")
    public ResponseEntity addCompetition(@RequestBody @Valid CompetitionRequest competitionRequest) {
        Competition competition = CompetitionMapper.mapCompetitionRequestToCompetition(competitionRequest);
        Competition competition1 = competitionService.addCompetition(competition);
        if(competition1 == null) {
            return ResponseMessage.badRequest("Failed To Create Competition");
        } else {
            return ResponseMessage.created("Competition Created Successfully", competition1);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY'))")
    public ResponseEntity updateCompetition(@RequestBody @Valid CompetitionRequest competitionRequest, @PathVariable Long id) {
        Competition competition = CompetitionMapper.mapCompetitionRequestToCompetition(competitionRequest);
        Competition competition1 = competitionService.updateCompetition(competition, id);
        if (competition1 == null) {
            return ResponseMessage.badRequest("Competition Not Updated");
        } else {
            return ResponseMessage.created("Competition Updated Successfully", competition1);
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and (hasRole('MANAGER') or hasRole('JURY'))")
    public ResponseEntity deleteCompetition(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseMessage.notFound("Competition Not Found");
        } else {
            competitionService.deleteCompetition(id);
            return ResponseMessage.ok("Competition Deleted Successfully", competition);
        }
    }
}

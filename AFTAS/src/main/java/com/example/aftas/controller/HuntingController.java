package com.example.aftas.controller;

import com.example.aftas.Dtos.request.HuntingRequest;
import com.example.aftas.entities.Hunt;
import com.example.aftas.mappers.HuntMapper;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.HuntingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingService huntingService;
    private final HuntMapper huntMapper;

    @GetMapping("")
    public ResponseEntity getHuntings() {
        List<Hunt> hunts = huntingService.getHuntings();
        if (hunts.isEmpty()) {
            return ResponseMessage.notFound("Huntings Not Found");
        } else {
            return ResponseMessage.ok("Success", hunts);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getHuntingById(@PathVariable Long id) {
        Hunt hunt = huntingService.getHuntingById(id);
        if (hunt == null) {
            return ResponseMessage.notFound("Hunting Not Found");
        } else {
            return ResponseMessage.ok("Success", hunt);
        }
    }

    @PostMapping("")
    public ResponseEntity addHunting(@RequestBody @Valid HuntingRequest huntingRequest) {
        Hunt hunt = huntMapper.mapHuntRequestToHunt(huntingRequest);
        Hunt hunt1 = huntingService.addHunting(hunt, huntingRequest.getHuntWeight());
        if(hunt1 == null) {
            return ResponseMessage.badRequest("Failed To Create Hunting");
        } else {
            return ResponseMessage.created("Hunting Created Successfully", hunt1);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHunting(@RequestBody HuntingRequest huntingRequest, @PathVariable Long id) {
        Hunt hunt = huntMapper.mapHuntRequestToHunt(huntingRequest);
        Hunt hunt1 = huntingService.updateHunting(hunt, id);
        if (hunt1 == null) {
            return ResponseMessage.badRequest("Hunting Not Updated");
        } else {
            return ResponseMessage.created("Hunting Updated Successfully", hunt1);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteHunting(@PathVariable Long id) {
        Hunt hunt = huntingService.getHuntingById(id);
        if (hunt == null) {
            return ResponseMessage.notFound("Hunting Not Found");
        } else {
            huntingService.deleteHunting(id);
            return ResponseMessage.ok("Hunting Deleted Successfully", hunt);
        }
    }
}

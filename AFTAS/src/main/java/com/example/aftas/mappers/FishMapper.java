package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.FishRequest;
import com.example.aftas.entities.Fish;
import com.example.aftas.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FishMapper {

    private final LevelService levelService;

    public Fish mapFishRequestToFish(FishRequest fishRequest) {
        return Fish.builder()
                .name(fishRequest.getName())
                .averageWeight(fishRequest.getAverageWeight())
                .level(levelService.getLevelByLevel(fishRequest.getLevel()))
                .build();
    }
}

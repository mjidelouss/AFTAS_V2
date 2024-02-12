package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.LevelRequest;
import com.example.aftas.entities.Level;

public class LevelMapper {

    public static Level mapLevelRequestToMapper(LevelRequest levelRequest) {
        return Level.builder()
                .level(levelRequest.getLevel())
                .points(levelRequest.getPoints())
                .description(levelRequest.getDescription())
                .build();
    }
}

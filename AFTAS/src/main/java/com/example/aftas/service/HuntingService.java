package com.example.aftas.service;

import com.example.aftas.entities.Hunt;

import java.util.List;

public interface HuntingService {
    Hunt getHuntingById(Long id);

    Hunt addHunting(Hunt hunt, Double huntedWeight);

    Hunt updateHunting(Hunt hunt, Long id);

    void deleteHunting(Long id);

    List<Hunt> getHuntings();
}

package com.example.aftas.service;

import com.example.aftas.entities.Fish;

import java.util.List;

public interface FishService {

    Fish getFishById(Long id);

    Fish getFishByName(String name);

    Fish addFish(Fish fish);

    Fish updateFish(Fish fish, Long id);

    void deleteFish(Long id);

    List<Fish> getFishes();
}

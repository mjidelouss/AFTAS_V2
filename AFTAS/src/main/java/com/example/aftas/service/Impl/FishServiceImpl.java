package com.example.aftas.service.Impl;

import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import com.example.aftas.service.LevelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelService levelService;

    @Override
    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElse(null);
    }

    @Override
    public Fish getFishByName(String name) {
        return fishRepository.findByName(name);
    }

    @Override
    public Fish addFish(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        Fish existingFish = getFishById(id);
        existingFish.setName(fish.getName());
        existingFish.setAverageWeight(fish.getAverageWeight());
        return fishRepository.save(existingFish);
    }

    @Override
    public void deleteFish(Long id) {
        fishRepository.deleteById(id);
    }

    @Override
    public List<Fish> getFishes() {
        return fishRepository.findAll();
    }
}

package com.example.aftas.service;

import com.example.aftas.entities.Level;

import java.util.List;

public interface LevelService {
    Level getLevelById(Long id);

    Level getLevelByLevel(Integer level);

    Level addLevel(Level level);

    Level updateLevel(Level level, Long id);

    void deleteLevel(Long id);

    List<Level> getLevels();
}

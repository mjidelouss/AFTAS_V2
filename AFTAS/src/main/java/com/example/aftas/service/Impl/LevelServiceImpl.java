package com.example.aftas.service.Impl;

import com.example.aftas.entities.Level;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    @Override
    public Level getLevelById(Long id) {
        return levelRepository.findById(id).orElse(null);
    }

    @Override
    public Level getLevelByLevel(Integer level) {
        return levelRepository.findByLevel(level);
    }

    @Override
    public Level addLevel(Level level) {
        validateLevel(level);
        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(Level level, Long id) {
        validateLevelInUpdate(level);
        Level existingLevel = getLevelById(id);
        existingLevel.setLevel(level.getLevel());
        existingLevel.setDescription(level.getDescription());
        existingLevel.setPoints(level.getPoints());
        return levelRepository.save(existingLevel);
    }

    @Override
    public void deleteLevel(Long id) {
        levelRepository.deleteById(id);
    }

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll();
    }

    public void validateLevel(Level newLevel) {
        Integer maxLevel = levelRepository.findMaxLevel();
        Integer maxPoints = levelRepository.findMaxPoints();
        Level level = levelRepository.findByLevel(newLevel.getLevel());

        if (level != null) {
            throw new IllegalArgumentException("Level already exists");
        }
        if (maxLevel != null && maxPoints != null && !(newLevel.getLevel() == maxLevel + 1 && newLevel.getPoints() > maxPoints)) {
            throw new IllegalArgumentException("Invalid level or points");
        }
    }

    public void validateLevelInUpdate(Level updatedLevel) {
        validateLevel(updatedLevel);
        Level previousLevel = levelRepository.findByLevel(updatedLevel.getLevel() - 1);
        Level nextLevel = levelRepository.findByLevel(updatedLevel.getLevel() + 1);

        if (previousLevel != null && updatedLevel.getPoints() <= previousLevel.getPoints()) {
            throw new IllegalArgumentException("Points must be higher than the previous level");
        }
        if (nextLevel != null && updatedLevel.getPoints() >= nextLevel.getPoints()) {
            throw new IllegalArgumentException("Points must be lower than the next level");
        }
    }
}

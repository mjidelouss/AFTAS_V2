package com.example.aftas.repository;

import com.example.aftas.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    @Query("SELECT MAX(l.level) FROM Level l")
    Integer findMaxLevel();

    @Query("SELECT MAX(l.points) FROM Level l")
    Integer findMaxPoints();

    Level findByLevel(Integer level);
}

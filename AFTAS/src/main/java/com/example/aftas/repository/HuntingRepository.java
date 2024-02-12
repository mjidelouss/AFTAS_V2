package com.example.aftas.repository;

import com.example.aftas.entities.Hunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunt, Long> {

    Hunt findByFish_NameAndMember_Id(String fishName, Long id);
}

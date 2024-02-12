package com.example.aftas.service.Impl;

import com.example.aftas.entities.*;
import com.example.aftas.enums.CompetitionStatus;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.HuntingService;
import com.example.aftas.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final RankingService rankingService;

    @Override
    public Hunt getHuntingById(Long id) {
        return huntingRepository.findById(id).orElse(null);
    }

    @Override
    public Hunt addHunting(Hunt hunt, Double huntedWeight) {
        String fishName = hunt.getFish().getName();
        Hunt existingHunt = huntingRepository.findByFish_NameAndMember_Id(fishName, hunt.getMember().getId());
        validateFishWeight(hunt, huntedWeight);
        if (hunt.getCompetition().getStatus() == CompetitionStatus.IN_PROGRESS) {
            if (existingHunt != null) {
                existingHunt.setNumberOfFish(existingHunt.getNumberOfFish() + hunt.getNumberOfFish());
                huntingRepository.save(existingHunt);
            } else {
                huntingRepository.save(hunt);
            }
            rankingService.updateScore(hunt.getMember(), hunt.getCompetition(), hunt.getFish());
        } else {
            throw new IllegalStateException("Cannot save hunt when the competition is not in progress");
        }
        return hunt;
    }

    @Override
    public Hunt updateHunting(Hunt hunt, Long id) {
        Hunt existingHunt = getHuntingById(id);
        existingHunt.setNumberOfFish(hunt.getNumberOfFish());
        return huntingRepository.save(existingHunt);
    }

    @Override
    public void deleteHunting(Long id) {
        huntingRepository.deleteById(id);
    }

    @Override
    public List<Hunt> getHuntings() {
        return huntingRepository.findAll();
    }

    public void validateFishWeight(Hunt hunt, Double huntedWeight) {
        if (huntedWeight < hunt.getFish().getAverageWeight()) {
            throw new IllegalArgumentException("Hunted weight must be equal or greater than average weight");
        }
    }
}

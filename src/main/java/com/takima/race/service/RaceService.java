package com.takima.race.service;

import com.takima.race.dto.RaceRequest;
import com.takima.race.entity.Race;
import com.takima.race.exception.NotFoundException;
import com.takima.race.repository.RaceRepository;
import com.takima.race.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Race> findAll(String location) {
        if (location != null && !location.isBlank()) {
            return raceRepository.findByLocation(location);
        }
        return raceRepository.findAll();
    }

    public Race findById(Long id) {
        return raceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Race not found"));
    }

    public Race create(RaceRequest request) {
        Race race = new Race();
        race.setName(request.getName());
        race.setDate(request.getDate());
        race.setLocation(request.getLocation());
        race.setMaxParticipants(request.getMaxParticipants());

        return raceRepository.save(race);
    }

    public long countParticipants(Long raceId) {
        findById(raceId);
        return registrationRepository.countByRace_Id(raceId);
    }
}
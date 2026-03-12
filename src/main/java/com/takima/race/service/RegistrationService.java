package com.takima.race.service;

import com.takima.race.dto.RegistrationRequest;
import com.takima.race.entity.Race;
import com.takima.race.entity.Registration;
import com.takima.race.entity.Runner;
import com.takima.race.exception.ConflictException;
import com.takima.race.exception.NotFoundException;
import com.takima.race.repository.RaceRepository;
import com.takima.race.repository.RegistrationRepository;
import com.takima.race.repository.RunnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            RunnerRepository runnerRepository,
            RaceRepository raceRepository
    ) {
        this.registrationRepository = registrationRepository;
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
    }

    public Registration register(Long raceId, RegistrationRequest request) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new NotFoundException("Race not found"));

        Runner runner = runnerRepository.findById(request.getRunnerId())
                .orElseThrow(() -> new NotFoundException("Runner not found"));

        if (registrationRepository.existsByRunner_IdAndRace_Id(runner.getId(), race.getId())) {
            throw new ConflictException("Runner already registered to this race");
        }

        long participantsCount = registrationRepository.countByRace_Id(raceId);
        if (participantsCount >= race.getMaxParticipants()) {
            throw new ConflictException("Race is full");
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

    public List<Runner> getParticipants(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new NotFoundException("Race not found"));

        return registrationRepository.findByRace_Id(race.getId())
                .stream()
                .map(Registration::getRunner)
                .toList();
    }

    public List<Race> getRacesByRunner(Long runnerId) {
        Runner runner = runnerRepository.findById(runnerId)
                .orElseThrow(() -> new NotFoundException("Runner not found"));

        return registrationRepository.findByRunner_Id(runner.getId())
                .stream()
                .map(Registration::getRace)
                .toList();
    }
}
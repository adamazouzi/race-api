package com.takima.race.repository;

import com.takima.race.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByRunner_IdAndRace_Id(Long runnerId, Long raceId);

    long countByRace_Id(Long raceId);

    List<Registration> findByRace_Id(Long raceId);

    List<Registration> findByRunner_Id(Long runnerId);
}
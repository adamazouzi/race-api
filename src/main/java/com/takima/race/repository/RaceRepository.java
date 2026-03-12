package com.takima.race.repository;

import com.takima.race.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {
    List<Race> findByLocation(String location);
}
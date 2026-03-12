package com.takima.race.repository;

import com.takima.race.entity.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Long> {
}
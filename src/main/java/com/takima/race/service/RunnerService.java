package com.takima.race.service;

import com.takima.race.dto.RunnerRequest;
import com.takima.race.entity.Runner;
import com.takima.race.exception.BadRequestException;
import com.takima.race.exception.NotFoundException;
import com.takima.race.repository.RunnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> findAll() {
        return runnerRepository.findAll();
    }

    public Runner findById(Long id) {
        return runnerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Runner not found"));
    }

    public Runner create(RunnerRequest request) {
        validateEmail(request.getEmail());

        Runner runner = new Runner();
        runner.setFirstName(request.getFirstName());
        runner.setLastName(request.getLastName());
        runner.setEmail(request.getEmail());
        runner.setAge(request.getAge());

        return runnerRepository.save(runner);
    }

    public Runner update(Long id, RunnerRequest request) {
        Runner runner = findById(id);
        validateEmail(request.getEmail());

        runner.setFirstName(request.getFirstName());
        runner.setLastName(request.getLastName());
        runner.setEmail(request.getEmail());
        runner.setAge(request.getAge());

        return runnerRepository.save(runner);
    }

    public void delete(Long id) {
        Runner runner = findById(id);
        runnerRepository.delete(runner);
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new BadRequestException("Invalid email");
        }
    }
}
package com.takima.race.controller;

import com.takima.race.dto.RunnerRequest;
import com.takima.race.entity.Runner;
import com.takima.race.service.RunnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runners")
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.findAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Runner> create(@RequestBody RunnerRequest request) {
        Runner runner = runnerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(runner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Runner> update(@PathVariable Long id, @RequestBody RunnerRequest request) {
        Runner runner = runnerService.update(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(runner);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        runnerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
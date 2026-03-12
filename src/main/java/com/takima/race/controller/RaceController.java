package com.takima.race.controller;

import com.takima.race.dto.CountResponse;
import com.takima.race.dto.RaceRequest;
import com.takima.race.entity.Race;
import com.takima.race.service.RaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        return raceService.findAll(location);
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Race> create(@RequestBody RaceRequest request) {
        Race race = raceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(race);
    }

    @GetMapping("/{raceId}/participants/count")
    public CountResponse countParticipants(@PathVariable Long raceId) {
        return new CountResponse(raceService.countParticipants(raceId));
    }
}
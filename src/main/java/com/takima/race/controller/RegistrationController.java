package com.takima.race.controller;

import com.takima.race.dto.RegistrationRequest;
import com.takima.race.entity.Race;
import com.takima.race.entity.Registration;
import com.takima.race.entity.Runner;
import com.takima.race.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/races/{raceId}/registrations")
    public ResponseEntity<Registration> register(
            @PathVariable Long raceId,
            @RequestBody RegistrationRequest request
    ) {
        Registration registration = registrationService.register(raceId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @GetMapping("/races/{raceId}/registrations")
    public List<Runner> getParticipants(@PathVariable Long raceId) {
        return registrationService.getParticipants(raceId);
    }

    @GetMapping("/runners/{runnerId}/races")
    public List<Race> getRacesByRunner(@PathVariable Long runnerId) {
        return registrationService.getRacesByRunner(runnerId);
    }
}
package com.takima.race.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public Registration() {}

    public Long getId() {
        return id;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
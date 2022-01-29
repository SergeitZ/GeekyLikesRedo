package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Avatar;
import com.geekylikes.app.models.Developer;
import com.geekylikes.app.repositories.AvatarRepository;
import com.geekylikes.app.repositories.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/developers")
public class DeveloperController {
    @Autowired
    DeveloperRepository repository;

    @Autowired
    AvatarRepository avatarRepository;

    @GetMapping
    public @ResponseBody List<Developer> getDevelopers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Developer getOneDeveloper (@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cohort/{cohort}")
    public ResponseEntity<List<Developer>> getOneDevelopersByCohort (@PathVariable Integer cohort) {
        return new ResponseEntity<>(repository.findAllByCohort(cohort, Sort.by("name")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Developer> createDeveloper (@RequestBody Developer newDeveloper) {
        return new ResponseEntity<>(repository.save(newDeveloper), HttpStatus.CREATED);
    }

    @PostMapping("/photo")
    public Developer addPhoto(@RequestBody Developer dev) {
        Developer developer = repository.findById(dev.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        developer.setAvatar(dev.getAvatar());

        if (developer.getAvatar() != null) {
            Avatar avatar = developer.getAvatar();
            avatar.setUrl(dev.getAvatar().getUrl());
            avatarRepository.save(avatar);
            return developer;
        }
        Avatar avatar = avatarRepository.save(dev.getAvatar());
        developer.setAvatar(avatar);
        return repository.save(developer);

    }


    @PutMapping("/{id}")
    public @ResponseBody Developer updateDeveloper (@PathVariable Long id, @RequestBody Developer updates) {
        Developer developer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) developer.setName(updates.getName());
        if (updates.getEmail() != null) developer.setEmail(updates.getEmail());
        if (updates.getCohort() != null) developer.setCohort(updates.getCohort());
        if (updates.getLanguages() != null) developer.setLanguages(updates.getLanguages());

        return repository.save(updates);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyDeveloper (@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/language")
    public Developer addLanguage(@RequestBody Developer updates) {
        Developer developer = repository.findById(updates.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        developer.getLanguages().addAll(updates.getLanguages());
        return repository.save(developer);
    }
}

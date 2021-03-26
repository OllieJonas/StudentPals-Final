package com.igpgroup17.studentpals.controllers;

import com.igpgroup17.studentpals.models.Organiser;
import com.igpgroup17.studentpals.services.OrganiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrganiserController {

    private final OrganiserService organiserService;

    @Autowired
    public OrganiserController(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    @GetMapping("/v1/organisers/{id}")
    public ResponseEntity<?> getOrganiserById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(organiserService.readOrganiser(id));
    }

    @PostMapping("/v1/organisers")
    public ResponseEntity<?> createOrganiser(@RequestBody Organiser organiser) {
        return ResponseEntity.ok(organiserService.createOrganiser(organiser));
    }

    @PutMapping("/v1/organisers")
    public ResponseEntity<?> updateOrganiser(@RequestBody Organiser organiser) {
        return ResponseEntity.ok(organiserService.updateOrganiser(organiser));
    }

    @DeleteMapping("/v1/organisers/{id}")
    public ResponseEntity<?> deleteOrganiserById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(organiserService.deleteOrganiser(id));
    }
}

package fr.epita.discover.presentation.controllers;

import fr.epita.discover.application.entities.ShuttleDTO;
import fr.epita.discover.application.services.ShuttleApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@RestController
@RequestMapping("/api/technicians/shuttles")
public class ShuttleController {

    private final ShuttleApplicationService shuttleService;

    
    @Autowired
    public ShuttleController(ShuttleApplicationService shuttleService) {
        this.shuttleService = shuttleService;
    }

    @GetMapping
    public List<ShuttleDTO> getAllShuttles() {
        return shuttleService.getAllShuttles();
    }

    @PostMapping("/create")
    public ResponseEntity<ShuttleDTO> createShuttle(@RequestBody ShuttleDTO shuttleDTO) {
        return ResponseEntity.status(201).body(shuttleService.createShuttle(shuttleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShuttle(@PathVariable Long id) {
        shuttleService.deleteShuttle(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ShuttleDTO> updateShuttleStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.status(HttpStatus.OK).body(shuttleService.updateShuttleStatus(id, status));
    }
}
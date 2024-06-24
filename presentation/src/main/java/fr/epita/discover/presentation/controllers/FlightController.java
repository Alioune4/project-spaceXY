package fr.epita.discover.presentation.controllers;

import fr.epita.discover.application.entities.FlightDTO;
import fr.epita.discover.application.services.FlightApplicationService;
import fr.epita.discover.domain.models.entity.Flight;
import fr.epita.discover.domain.models.valueObject.FlightStatus;
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
@RequestMapping("/api/planners/flights")
public class FlightController {

    private final FlightApplicationService flightService;
    @Autowired

    public FlightController(FlightApplicationService flightService) {
        this.flightService = flightService;
    }


    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(flightDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<FlightDTO> updateFlightStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(flightService.updateFlightStatus(id, FlightStatus.valueOf(status)));
    }
}
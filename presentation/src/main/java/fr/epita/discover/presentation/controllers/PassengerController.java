package fr.epita.discover.presentation.controllers;

import fr.epita.discover.application.entities.FuturFlightDto;
import fr.epita.discover.application.services.PassengerApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerApplicationService passengerService;

    @Autowired
    public PassengerController(PassengerApplicationService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/future")
    public ResponseEntity<List<FuturFlightDto>> getFutureFlights() {
        return ResponseEntity.ok(passengerService.getFutureFlights());
    }

    @PostMapping("/register/{flightId}")
    public ResponseEntity<?> registerToFlight(@PathVariable Long flightId) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(passengerService.registerToFlight(flightId, principal.getUsername()));
    }

}
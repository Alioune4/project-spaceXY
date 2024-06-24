package fr.epita.discover.domain.services;

import fr.epita.discover.domain.models.entity.Flight;
import fr.epita.discover.domain.models.valueObject.FlightStatus;
import fr.epita.discover.domain.repositories.FlightRepository;
import fr.epita.discover.infrastructure.config.MailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Service
public class FlightDomainService {
    private final MailService mailService;
    private final FlightRepository flightRepository;

    public FlightDomainService(FlightRepository flightRepository, MailService mailService) {
        this.flightRepository = flightRepository;
        this.mailService = mailService;
    }

    public List<Flight> getFutureFlights() {
        return flightRepository.findAll().stream()
                .filter(flight -> flight.getDateTime().isAfter(LocalDateTime.now())
                        && flight.getPassengers().size() < flight.getShuttle().getCapacity())
                .toList(); }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
        boolean alreadyFlought = flightRepository.findAll().stream()
                .anyMatch(f -> f.getDateTime().getMonth().equals(flight.getDateTime().getMonth()) && f.getDateTime().getYear() == flight.getDateTime().getYear());
        if(alreadyFlought) {
            throw new RuntimeException("A flight has already been scheduled this month");
        }
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight updateFlightStatus(Long id, FlightStatus status) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        flight.setStatus(status);
        if(flight.getPassengers() != null && !flight.getPassengers().isEmpty()) {
            flight.getPassengers().forEach(passenger -> mailService.sendMail(passenger.getEmail(), "Flight status update", "Your flight status has been updated to " + status.name()));
        }
        return flightRepository.save(flight);
    }
}
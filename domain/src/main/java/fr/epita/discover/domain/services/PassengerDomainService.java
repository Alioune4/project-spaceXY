package fr.epita.discover.domain.services;

import fr.epita.discover.domain.models.entity.Flight;
import fr.epita.discover.domain.models.entity.Passenger;
import fr.epita.discover.domain.models.valueObject.FlightStatus;
import fr.epita.discover.domain.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */

@Service
public class PassengerDomainService {
    private final PassengerRepository passengerRepository;
    private final FlightDomainService flightDomainService;

    public PassengerDomainService(PassengerRepository passengerRepository, FlightDomainService flightDomainService) {
        this.passengerRepository = passengerRepository;
        this.flightDomainService = flightDomainService;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.findPassengerByEmail(email);
    }


    public Passenger registerToFlight(Long flightId, String username) {
        Flight flight = flightDomainService.getFlightById(flightId);
        if (flight == null) {
            throw new RuntimeException("Flight not found");
        }
        if (flight.getPassengers().size() >= flight.getShuttle().getCapacity()) {
            throw new RuntimeException("Flight is full");
        }
        if (!flight.isFutureFlight()) {
            throw new RuntimeException("Flight is already passed");
        }

        if(flight.getStatus() != FlightStatus.OK){
            throw new RuntimeException("Flight is not ready for passengers");
        }

        Passenger passenger = flight.getPassengers().stream().filter(p -> p.getEmail().equals(username)).findFirst().orElse(null);
        if(passenger != null){
            throw new RuntimeException("Passenger is already registered to this flight");
        }
        Passenger newPassenger= new Passenger();
        newPassenger.setEmail(username);
        newPassenger.setFlight(flight);
        newPassenger=passengerRepository.save(newPassenger);

        flight.getPassengers().add(newPassenger);


        return newPassenger;
    }
}
package fr.epita.discover.application.services;

import fr.epita.discover.application.entities.FuturFlightDto;
import fr.epita.discover.application.entities.PassengerDTO;
import fr.epita.discover.application.entities.RevisionDTO;
import fr.epita.discover.domain.models.entity.Flight;
import fr.epita.discover.domain.models.entity.Passenger;
import fr.epita.discover.domain.models.entity.Revision;
import fr.epita.discover.domain.services.FlightDomainService;
import fr.epita.discover.domain.services.PassengerDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Service
public class PassengerApplicationService {

    private final PassengerDomainService passengerDomainService;
    private final FlightDomainService flightDomainService;

    @Autowired
    public PassengerApplicationService(PassengerDomainService passengerDomainService, FlightDomainService flightDomainService) {
        this.passengerDomainService = passengerDomainService;
        this.flightDomainService = flightDomainService;
    }

    public List<PassengerDTO> getAllPassengers() {
        return passengerDomainService.getAllPassengers().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FuturFlightDto> getFutureFlights() {
        return flightDomainService.getFutureFlights().stream()
                .map(this::convertFuturFlightDto)
                .collect(Collectors.toList());
    }

    public PassengerDTO createPassenger(Passenger passenger) {
        return convertToDTO(passengerDomainService.createPassenger(passenger));
    }

    private FuturFlightDto convertFuturFlightDto(Flight flight){
        FuturFlightDto futurFlightDto = new FuturFlightDto();
        futurFlightDto.setId(flight.getId());
        futurFlightDto.setDateTime(flight.getDateTime());
        futurFlightDto.setCapacity(flight.getShuttle().getCapacity());
        futurFlightDto.setRemainingSeats(flight.getShuttle().getCapacity() - flight.getPassengers().size());
        futurFlightDto.setShuttleId(flight.getShuttle().getId());

        return futurFlightDto;
    }

    private RevisionDTO convertRevisionToDTO(Revision revision) {
        RevisionDTO revisionDTO = new RevisionDTO();
        revisionDTO.setDate(revision.getDate());
        revisionDTO.setShuttleId(revision.getShuttle().getId());
        return revisionDTO;
    }

    private PassengerDTO convertToDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setId(passenger.getId());
        passengerDTO.setEmail(passenger.getEmail());
        passengerDTO.setFlightId(passenger.getFlight().getId());
        return passengerDTO;
    }

    public PassengerDTO registerToFlight(Long flightId, String username) {
        Passenger passenger =passengerDomainService.registerToFlight(flightId, username);
        return convertToDTO(passenger);
    }

}

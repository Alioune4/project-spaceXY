package fr.epita.discover.application.services;

import fr.epita.discover.application.entities.FlightDTO;
import fr.epita.discover.domain.models.entity.Flight;
import fr.epita.discover.domain.models.entity.Shuttle;
import fr.epita.discover.domain.models.valueObject.FlightStatus;
import fr.epita.discover.domain.models.valueObject.ShuttleStatus;
import fr.epita.discover.domain.services.FlightDomainService;
import fr.epita.discover.domain.services.ShuttleDomainService;
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
public class FlightApplicationService {

    private final FlightDomainService flightDomainService;
    private final ShuttleDomainService shuttleDomainService;

    @Autowired
    public FlightApplicationService(FlightDomainService flightDomainService, ShuttleDomainService shuttleDomainService) {
        this.flightDomainService = flightDomainService;
        this.shuttleDomainService = shuttleDomainService;
    }

    public List<FlightDTO> getAllFlights() {
        return flightDomainService.getAllFlights().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteFlight(Long id) {
        flightDomainService.deleteFlight(id);
    }

    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setDateTime(flightDTO.getDateTime());
        Shuttle shuttle =shuttleDomainService.getShuttleById(flightDTO.getShuttleId());
        if(shuttle.getStatus() == ShuttleStatus.OBSOLETE) {
            throw new RuntimeException("Shuttle is obsolete. Cannot create flight.");
        }
        flight.setShuttle(shuttle);
        flight.setStatus(FlightStatus.WAITING_FOR_GEARCHECK);

        return convertToDTO(flightDomainService.createFlight(flight));
    }

    public FlightDTO updateFlightStatus(Long id, FlightStatus status) {
        return convertToDTO(flightDomainService.updateFlightStatus(id, status));
    }

    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setDateTime(flight.getDateTime());
        flightDTO.setShuttleId(flight.getShuttle().getId());
        flightDTO.setStatus(flight.getStatus().name());
        return flightDTO;
    }


}

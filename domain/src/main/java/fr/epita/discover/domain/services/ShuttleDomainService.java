package fr.epita.discover.domain.services;

import fr.epita.discover.domain.models.entity.Shuttle;
import fr.epita.discover.domain.models.valueObject.ShuttleStatus;
import fr.epita.discover.domain.repositories.FlightRepository;
import fr.epita.discover.domain.repositories.ShuttleRepository;
import fr.epita.discover.infrastructure.config.MailService;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Service
public class ShuttleDomainService {
    private final ShuttleRepository shuttleRepository;
    private final FlightRepository flightRepository;
    private final MailService mailService;

    public ShuttleDomainService(ShuttleRepository shuttleRepository,FlightRepository flightRepository, MailService mailService) {
        this.shuttleRepository = shuttleRepository;
        this.flightRepository = flightRepository;
        this.mailService = mailService;
    }

    public List<Shuttle> getAllShuttles() {
        return shuttleRepository.findAll();
    }

    public Shuttle getShuttleById(Long id) {
        return shuttleRepository.findById(id).orElseThrow(() -> new RuntimeException("Shuttle not found"));
    }

    public Shuttle createShuttle(Shuttle shuttle) {
        return shuttleRepository.save(shuttle);
    }

    public void deleteShuttle(Long id) {
        shuttleRepository.deleteById(id);
    }

    public Shuttle updateShuttleStatus(Long id, ShuttleStatus status) {
        Shuttle shuttle = shuttleRepository.findById(id).orElseThrow(() -> new RuntimeException("Shuttle not found"));
        shuttle.setStatus(status);
        if(shuttle.getStatus() == ShuttleStatus.OBSOLETE) {
            flightRepository.findAllByShuttleId(id).forEach(flight -> {
                flight.getPassengers().forEach(passenger -> {
                    mailService.sendMail(passenger.getEmail(), "Shuttle is obsolete", "The shuttle you were supposed to take is now obsolete. Sorry for the inconvenience.");
                });
            });
        }
        return shuttleRepository.save(shuttle);
    }
}
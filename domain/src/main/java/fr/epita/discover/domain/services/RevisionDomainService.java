package fr.epita.discover.domain.services;

import fr.epita.discover.domain.models.entity.Revision;
import fr.epita.discover.domain.models.valueObject.FlightStatus;
import fr.epita.discover.domain.repositories.RevisionRepository;
import org.springframework.stereotype.Service;

import java.time.chrono.ChronoLocalDate;
import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Service
public class RevisionDomainService {
    private final RevisionRepository revisionRepository;
    private final FlightDomainService flightDomainService;

    public RevisionDomainService(RevisionRepository revisionRepository,FlightDomainService flightDomainService) {
        this.revisionRepository = revisionRepository;
        this.flightDomainService = flightDomainService;
    }

    public List<Revision> getAllRevisions() {
        return revisionRepository.findAll();
    }

    public Revision createRevision(Revision revision) {
        this.flightDomainService.getAllFlights().stream()
                .filter(flight -> flight.getShuttle().getId().equals(revision.getShuttle().getId()))
                .filter(flight -> flight.getStatus().equals(FlightStatus.WAITING_FOR_GEARCHECK))
                .forEach(flight ->{
                    if(revision.getDate().isBefore(ChronoLocalDate.from(flight.getDateTime()))){
                        flight.setStatus(FlightStatus.OK);
                    }
                });
        return revisionRepository.save(revision);
    }

    public void deleteRevision(Long id) {
        revisionRepository.deleteById(id);
    }
}
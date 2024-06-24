package fr.epita.discover.domain.repositories;

import fr.epita.discover.domain.models.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByShuttleId(Long id);
}
package fr.epita.discover.domain.repositories;

import fr.epita.discover.domain.models.entity.Shuttle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Repository
public interface ShuttleRepository extends JpaRepository<Shuttle, Long> {
}
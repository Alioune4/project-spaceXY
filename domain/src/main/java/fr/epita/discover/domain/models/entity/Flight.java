package fr.epita.discover.domain.models.entity;

import fr.epita.discover.domain.models.valueObject.FlightStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "shuttle_id")
    private Shuttle shuttle;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @OneToMany(mappedBy = "flight")
    private List<Passenger> passengers;

    public int getRemainingSeats() {
        return shuttle.getCapacity() - passengers.size();
    }

    public boolean isFutureFlight() {
        return dateTime.isAfter(LocalDateTime.now());
    }

}


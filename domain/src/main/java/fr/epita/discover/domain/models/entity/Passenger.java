package fr.epita.discover.domain.models.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Entity
@Data
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
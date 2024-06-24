package fr.epita.discover.domain.models.entity;

import jakarta.persistence.*;
import lombok.*;
import fr.epita.discover.domain.models.valueObject.ShuttleStatus;

import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */

@Entity
@Data
public class Shuttle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @Enumerated(EnumType.STRING)
    private ShuttleStatus status;

    @OneToMany(mappedBy = "shuttle")
    private List<Revision> revisions;

    @OneToMany(mappedBy = "shuttle")
    private List<Flight> flights;

}


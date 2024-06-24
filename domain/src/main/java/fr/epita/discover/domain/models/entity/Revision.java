package fr.epita.discover.domain.models.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Entity
@Data
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "shuttle_id")
    private Shuttle shuttle;
}
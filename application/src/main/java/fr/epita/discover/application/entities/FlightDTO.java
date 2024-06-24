package fr.epita.discover.application.entities;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Data
public class FlightDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Long shuttleId;
    private String status;
}
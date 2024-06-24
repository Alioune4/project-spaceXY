package fr.epita.discover.application.entities;

import lombok.Data;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Data
public class PassengerDTO {
    private Long id;
    private String email;
    private Long flightId;
}
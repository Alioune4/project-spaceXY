package fr.epita.discover.application.entities;

import lombok.Data;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */

@Data
public class ShuttleDTO {
    private Long id;
    private String name;
    private int capacity;
    private String status;
}
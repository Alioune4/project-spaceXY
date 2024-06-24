package fr.epita.discover.application.entities;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@Data
public class RevisionDTO {
    private LocalDate date;
    private Long shuttleId;
}
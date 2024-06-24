package fr.epita.discover.application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class FuturFlightDto {

    private Long id;
    private LocalDateTime dateTime;
    private Long shuttleId;

    private int remainingSeats;
    private int capacity;

}

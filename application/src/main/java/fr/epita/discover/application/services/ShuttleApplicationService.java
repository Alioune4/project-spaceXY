package fr.epita.discover.application.services;

import fr.epita.discover.application.entities.ShuttleDTO;
import fr.epita.discover.domain.models.entity.Shuttle;
import fr.epita.discover.domain.services.ShuttleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.epita.discover.domain.models.valueObject.ShuttleStatus;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Service
public class ShuttleApplicationService {

    private final ShuttleDomainService shuttleDomainService;

    @Autowired
    public ShuttleApplicationService(ShuttleDomainService shuttleDomainService) {
        this.shuttleDomainService = shuttleDomainService;
    }

    public List<ShuttleDTO> getAllShuttles() {
        return shuttleDomainService.getAllShuttles().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ShuttleDTO createShuttle(ShuttleDTO shuttleDTO) {
        Shuttle shuttle = new Shuttle();
        shuttle.setName(shuttleDTO.getName());
        shuttle.setCapacity(shuttleDTO.getCapacity());
        shuttle.setStatus(ShuttleStatus.OK);
        if(shuttleDTO.getCapacity()> 5 || shuttleDTO.getCapacity() < 3){ //TODO: use spring validation
            throw new RuntimeException("Shuttle capacity must be between 1 and 5");
        }
        Shuttle createdShuttle = shuttleDomainService.createShuttle(shuttle);
        return convertToDTO(createdShuttle);
    }

    public void deleteShuttle(Long id) {
        shuttleDomainService.deleteShuttle(id);
    }

    public ShuttleDTO updateShuttleStatus(Long id, String status) {
        Shuttle updatedShuttle = shuttleDomainService.updateShuttleStatus(id, ShuttleStatus.valueOf(status));
        return convertToDTO(updatedShuttle);
    }

    private ShuttleDTO convertToDTO(Shuttle shuttle) {
        ShuttleDTO shuttleDTO = new ShuttleDTO();
        shuttleDTO.setId(shuttle.getId());
        shuttleDTO.setName(shuttle.getName());
        shuttleDTO.setCapacity(shuttle.getCapacity());
        shuttleDTO.setStatus(shuttle.getStatus().name());
        return shuttleDTO;
    }
}

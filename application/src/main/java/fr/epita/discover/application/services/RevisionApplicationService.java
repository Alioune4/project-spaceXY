package fr.epita.discover.application.services;

import fr.epita.discover.application.entities.RevisionDTO;
import fr.epita.discover.domain.models.entity.Revision;
import fr.epita.discover.domain.services.RevisionDomainService;
import fr.epita.discover.domain.services.ShuttleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 24/06/2024
 */
@Service
public class RevisionApplicationService {

    private final RevisionDomainService revisionDomainService;

    private final ShuttleDomainService shuttleDomainService;

    @Autowired
    public RevisionApplicationService(RevisionDomainService revisionDomainService, ShuttleDomainService shuttleDomainService) {
        this.revisionDomainService = revisionDomainService;
        this.shuttleDomainService = shuttleDomainService;
    }

    public List<RevisionDTO> getAllRevisions() {
        return revisionDomainService.getAllRevisions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public RevisionDTO createRevision(RevisionDTO revisionDTO) {
        Revision revision = new Revision();
        revision.setDate(revisionDTO.getDate());
        revision.setShuttle(shuttleDomainService.getShuttleById(revisionDTO.getShuttleId()));

        return convertToDTO(revisionDomainService.createRevision(revision));
    }

    private RevisionDTO convertToDTO(Revision revision) {
        RevisionDTO revisionDTO = new RevisionDTO();
        revisionDTO.setShuttleId(revision.getShuttle().getId());
        revisionDTO.setDate(revision.getDate());
        return revisionDTO;
    }

    public void deleteRevision(Long id) {
        revisionDomainService.deleteRevision(id);
    }
}

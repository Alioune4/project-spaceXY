package fr.epita.discover.presentation.controllers;

import fr.epita.discover.application.entities.RevisionDTO;
import fr.epita.discover.application.services.RevisionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lummix (julienmeyer32@gmail.com)
 * @project spacexy
 * 23/06/2024
 */
@RestController
@RequestMapping("/api/technicians/revisions")
public class RevisionController {

    private final RevisionApplicationService revisionService;

    @Autowired
    public RevisionController(RevisionApplicationService revisionService) {
        this.revisionService = revisionService;
    }

    @GetMapping
    public List<RevisionDTO> getAllRevisions() {
        return revisionService.getAllRevisions();
    }

    @PostMapping
    public ResponseEntity<RevisionDTO> createRevision(@RequestBody RevisionDTO revisionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(revisionService.createRevision(revisionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRevision(@PathVariable Long id) {
        revisionService.deleteRevision(id);
        return ResponseEntity.noContent().build();
    }
}

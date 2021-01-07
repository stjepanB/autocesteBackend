package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.highwaySections.HighwaySectionDto;
import diplomski.autoceste.services.HighwaySectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class HighwaySectionController {

    private final HighwaySectionService highwaySectionService;

    @Autowired
    public HighwaySectionController(HighwaySectionService highwaySectionService) {
        this.highwaySectionService = highwaySectionService;
    }

    @GetMapping(value = "/sections", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<HighwaySectionDto>> getHighwaySections() {
        List<HighwaySectionDto> sections = highwaySectionService.getHighwaySections().stream()
                .map(HighwaySectionDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.of(Optional.ofNullable(sections));
    }
}

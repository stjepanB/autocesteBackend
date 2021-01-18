package diplomski.autoceste.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class AdminInfoController {

    @GetMapping("/admin")
    public ResponseEntity<Boolean> isUserAdmin(Authentication authentication) {
        if (authentication.getAuthorities().stream().filter(e -> e.getAuthority().equals("ROLE_ADMIN")).count() == 1) {
            return ResponseEntity.of(Optional.of(true));
        }
        return ResponseEntity.of(Optional.of(false));
    }

}

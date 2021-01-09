package diplomski.autoceste.controllers;

import diplomski.autoceste.forms.ProfileDto;
import diplomski.autoceste.forms.RegisterPrivateUserDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.services.PrivateUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class PrivateUserController {

    private final PrivateUserServiceImpl service;

    @Autowired
    public PrivateUserController(PrivateUserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public HttpStatus register (@RequestBody RegisterPrivateUserDto dto){

        return service.register((dto)) ? HttpStatus.OK : HttpStatus.CONFLICT;
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<ProfileDto> profile(Authentication authentication){
        String email = authentication.getName();
        PrivateUser user = service.getPrivateUserByEmail(email);
        ProfileDto dto = new ProfileDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(email);

        return ResponseEntity.of(Optional.of(dto));
    }
}

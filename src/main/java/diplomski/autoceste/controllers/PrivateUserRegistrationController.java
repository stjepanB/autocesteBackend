package diplomski.autoceste.controllers;

import diplomski.autoceste.formsData.RegisterPrivateUserDto;
import diplomski.autoceste.model.PrivateUser;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import diplomski.autoceste.service.PrivateUserRegistrationService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
public class PrivateUserRegistrationController {

    private PrivateUserRegistrationService service;

    @Autowired
    public PrivateUserRegistrationController(PrivateUserRegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public HttpStatus register (@RequestBody RegisterPrivateUserDto dto){

        return service.register((dto)) ? HttpStatus.OK : HttpStatus.CONFLICT;
    }
}

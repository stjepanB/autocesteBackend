package diplomski.autoceste.controllers;

import diplomski.autoceste.formsData.RegisterUserDto;
import diplomski.autoceste.model.User;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import diplomski.autoceste.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrivateUserRegistrationController {

    private UserRepository repository;
    private PasswordConfig passwordConfig;

    @Autowired
    public PrivateUserRegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public HttpStatus register (@RequestBody RegisterUserDto userDto){

        User user = new User();
        user.setRole(AutocesteUserRole.USER.name());
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        //TODO continue implementation
        return HttpStatus.OK;
    }
}

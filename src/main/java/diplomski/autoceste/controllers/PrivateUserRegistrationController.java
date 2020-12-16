package diplomski.autoceste.controllers;

import diplomski.autoceste.formsData.RegisterPrivateUserDto;
import diplomski.autoceste.model.PrivateUser;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PrivateUserRegistrationController {

    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PrivateUserRegistrationController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public HttpStatus register (@RequestBody RegisterPrivateUserDto dto){

        PrivateUser privateUser = new PrivateUser();
        privateUser.setRole(AutocesteUserRole.USER.name());
        privateUser.setAccountNonExpired(true);
        privateUser.setEnabled(true);
        privateUser.setAccountNonLocked(true);
        privateUser.setCredentialsNonExpired(true);
        privateUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        privateUser.setEmail(dto.getEmail());
        privateUser.setFirstName(dto.getFirstName());
        privateUser.setLastName(dto.getLastName());
        privateUser.setInvalid(dto.isInvalid());
        privateUser.setAddress(dto.getAddress());
        privateUser.setOib(dto.getOib());

        repository.save(privateUser);
        return HttpStatus.OK;
    }
}

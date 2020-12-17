package diplomski.autoceste.service;

import diplomski.autoceste.formsData.RegisterPrivateUserDto;
import diplomski.autoceste.model.PrivateUser;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrivateUserRegistrationService {

    private UserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public PrivateUserRegistrationService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean register(RegisterPrivateUserDto dto){
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

        try {
            repository.saveAndFlush(privateUser);
        }catch (DataIntegrityViolationException e){
            return false;
        }

        return true;
    }
}

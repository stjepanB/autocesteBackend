package diplomski.autoceste.services;

import diplomski.autoceste.forms.RegisterPrivateUserDto;
import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.repositories.PrivateUserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrivateUserServiceImpl implements PrivateUserService {

    private PrivateUserRepository repository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public PrivateUserServiceImpl(PrivateUserRepository repository, PasswordEncoder passwordEncoder) {
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
            repository.save(privateUser);
        }catch (DataIntegrityViolationException e){
            return false;
        }

        return true;
    }

    @Override
    public Long getPrivateUserIdByEmail(String email) {
        PrivateUser user = repository.findByEmail(email);
        return user.getId();
    }

    @Override
    public PrivateUser getPrivateUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}

package diplomski.autoceste.auth;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.repositories.PrivateUserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutocesteUserDetailsDaoService implements AutocesteUserDetailsDao {

    private final PrivateUserRepository repository;

    @Autowired
    public AutocesteUserDetailsDaoService(PrivateUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AutocesteUserDetails> selectApplicationUserByEmail(String email) {

        PrivateUser privateUser = repository.findByEmail(email);
        AutocesteUserDetails userDetails = null;

        if (privateUser != null) {
            AutocesteUserRole role = AutocesteUserRole.valueOf(privateUser.getRole());

            userDetails = new AutocesteUserDetails(
                    role.getGrauntedAuthorities(),
                    privateUser.getPassword(),
                    privateUser.getEmail(),
                    privateUser.isAccountNonExpired(),
                    privateUser.isAccountNonLocked(),
                    privateUser.isCredentialsNonExpired(),
                    privateUser.isEnabled());
        }

        return userDetails == null ? Optional.empty() : Optional.of(userDetails);
    }
}

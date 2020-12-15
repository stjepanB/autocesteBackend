package diplomski.autoceste.auth;

import diplomski.autoceste.model.User;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutocesteUserDetailsDaoService implements AutocesteUserDetailsDao {

    private final UserRepository repository;

    @Autowired
    public AutocesteUserDetailsDaoService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<AutocesteUserDetails> selectApplicationUserByEmail(String email) {

        User user = repository.findByEmail(email);
        AutocesteUserDetails userDetails = null;

        if (user != null) {
            AutocesteUserRole role = AutocesteUserRole.valueOf(user.getRole());
            userDetails = new AutocesteUserDetails(
                    role.getGrauntedAuthorities(),
                    user.getPassword(),
                    user.getEmailAddress(),
                    user.isAccountNonExpired(),
                    user.isAccountNonLocked(),
                    user.isCredentialsNonExpired(),
                    user.isEnabled());
        }


        return userDetails == null ? Optional.empty() : Optional.of(userDetails);
    }
}

package diplomski.autoceste.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static diplomski.autoceste.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeUserApplicationDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeUserApplicationDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Arrays.asList(
                new ApplicationUser(STUDENT.getGrauntedAuthorities(),
                        passwordEncoder.encode("password"),
                        "slavko",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(ADMIN.getGrauntedAuthorities(),
                        passwordEncoder.encode("password"),
                        "miroslav",
                        true,
                        true,
                        true,
                        true

                ),
                new ApplicationUser(ADMINTRAINEE.getGrauntedAuthorities(),
                        passwordEncoder.encode("password"),
                        "zdeslav",
                        true,
                        true,
                        true,
                        true

                )
        );

        return applicationUsers;
    }
}

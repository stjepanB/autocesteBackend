package diplomski.autoceste;

import diplomski.autoceste.model.PrivateUser;
import diplomski.autoceste.repositories.UserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AutocesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutocesteApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) throws Exception {
        return (String[] args) -> {
            PrivateUser user1 = new PrivateUser();
            PrivateUser user2 = new PrivateUser();

            user1.setRole(AutocesteUserRole.USER.name());
            user1.setAccountNonExpired(true);
            user1.setEnabled(true);
            user1.setAccountNonLocked(true);
            user1.setCredentialsNonExpired(true);
            user1.setPassword(passwordEncoder.encode("pass"));
            user1.setEmail("slavko@firma.com");
            user1.setFirstName("Slavko");
            user1.setLastName("Bekavac");
            user1.setInvalid(false);
            user1.setAddress("Prvomajska 1 ");
            user1.setOib(74060539451L);

            user2.setRole(AutocesteUserRole.USER.name());
            user2.setAccountNonExpired(true);
            user2.setEnabled(true);
            user2.setAccountNonLocked(true);
            user2.setCredentialsNonExpired(true);
            user2.setPassword(passwordEncoder.encode("pass"));
            user2.setEmail("mijomir@firma.com");
            user2.setFirstName("Mijomir");
            user2.setLastName("Bubalo");
            user2.setInvalid(false);
            user2.setAddress("Zdenci 1 ");
            user2.setOib(92249397606L);

            userRepository.save(user1);
            userRepository.save(user2);

        };
    }

}

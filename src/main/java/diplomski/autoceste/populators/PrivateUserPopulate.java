package diplomski.autoceste.populators;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.repositories.PrivateUserRepository;
import diplomski.autoceste.security.AutocesteUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PrivateUserPopulate {

    PasswordEncoder passwordEncoder;
    PrivateUserRepository privateUserRepository;

    @Autowired
    public PrivateUserPopulate(PasswordEncoder passwordEncoder, PrivateUserRepository privateUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.privateUserRepository = privateUserRepository;
    }

    public void populate(){
        PrivateUser user1 = new PrivateUser();

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
        user1.setOib("74060539451");

        PrivateUser user2 = new PrivateUser();
        user2.setRole(AutocesteUserRole.ADMIN.name());
        user2.setAccountNonExpired(true);
        user2.setEnabled(true);
        user2.setAccountNonLocked(true);
        user2.setCredentialsNonExpired(true);
        user2.setPassword(passwordEncoder.encode("pass"));
        user2.setEmail("admin@autocesta.com");
        user2.setFirstName("Mijomir");
        user2.setLastName("Bubalo");
        user2.setInvalid(false);
        user2.setAddress("Zdenci 1 ");
        user2.setOib("92249397606");

        privateUserRepository.save(user1);
        privateUserRepository.save(user2);
    }

}

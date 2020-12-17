package diplomski.autoceste.repositories;

import diplomski.autoceste.model.PrivateUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<PrivateUser,Long> {

    PrivateUser findByEmail(String email);
}

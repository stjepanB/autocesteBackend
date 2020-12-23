package diplomski.autoceste.repositories;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByPrivateUser(PrivateUser privateUser);
}

package diplomski.autoceste.repositories;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByPrivateUser(PrivateUser privateUser);

    @Query(value = "SELECT column_name, data_type FROM information_schema.columns " +
            "WHERE  table_name = 'vehicle' \n", nativeQuery = true)
    List<Object[]> getColumnsWithTypes();
}

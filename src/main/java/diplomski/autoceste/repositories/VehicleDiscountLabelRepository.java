package diplomski.autoceste.repositories;

import diplomski.autoceste.models.VehicleDiscountLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleDiscountLabelRepository extends JpaRepository<VehicleDiscountLabel, Long> {
    List<VehicleDiscountLabel> findAllByName(String name);
}

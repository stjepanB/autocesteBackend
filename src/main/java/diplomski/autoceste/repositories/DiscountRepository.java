package diplomski.autoceste.repositories;

import diplomski.autoceste.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}

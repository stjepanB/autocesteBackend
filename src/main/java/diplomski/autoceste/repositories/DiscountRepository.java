package diplomski.autoceste.repositories;

import diplomski.autoceste.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findByStartDateLessThanAndEndDateGreaterThan(LocalDate localDate);
}

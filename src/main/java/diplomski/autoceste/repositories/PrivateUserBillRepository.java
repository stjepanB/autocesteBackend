package diplomski.autoceste.repositories;

import diplomski.autoceste.models.PrivateUser;
import diplomski.autoceste.models.PrivateUserBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateUserBillRepository extends JpaRepository<PrivateUserBill, Long> {

    List<PrivateUserBill> findAllByPrivateUser(PrivateUser privateUser);
}

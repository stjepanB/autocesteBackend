package diplomski.autoceste.repositories;

import diplomski.autoceste.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}

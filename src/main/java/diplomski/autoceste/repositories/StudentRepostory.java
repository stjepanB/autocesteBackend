package diplomski.autoceste.repositories;

import diplomski.autoceste.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepostory extends JpaRepository<Student,Integer> {
}

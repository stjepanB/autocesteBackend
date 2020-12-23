package diplomski.autoceste.repositories.tutorial;

import diplomski.autoceste.models.tutorial.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepostory extends JpaRepository<Student,Integer> {
}

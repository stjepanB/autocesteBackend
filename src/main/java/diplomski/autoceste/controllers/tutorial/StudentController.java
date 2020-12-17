package diplomski.autoceste.controllers.tutorial;


import diplomski.autoceste.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static List<Student> students = Arrays.asList(
            new Student(1,"Tamara"),
            new Student(2, "Mladen"),
            new Student(3, "Ankica"),
            new Student(4, "Joško")
    );

    @GetMapping()
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        return students.stream()
                .filter(e -> studentId.equals(e.getId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }



}

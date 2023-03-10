package springboot.mongo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;


public interface StudentService {
    ResponseObj getAllStudent();
    ResponseObj addStudent(Student newStudent);
    ResponseObj getStudentByName(String name);
    ResponseObj updateStudent(Student newStudent, String studentId);
    ResponseObj deleteStudent(String studentId);
    ResponseObj getStudentBetweenAge(Integer minAge, Integer maxAge);
    Page<Student> filterStudent(String firstName, Integer minAge, Integer maxAge, String country, Pageable pageable);
}

package springboot.mongo.services;

import org.springframework.web.bind.annotation.PathVariable;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;


public interface StudentService {
    ResponseObj getAllStudent();
    ResponseObj addStudent(Student newStudent);
    ResponseObj getStudentByName(String name);
    ResponseObj updateStudent(Student newStudent, String studentId);
    ResponseObj deleteStudent(@PathVariable String studentId);
}

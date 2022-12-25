package springboot.mongo.services;

import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;


public interface StudentService {
    ResponseObj getAllStudent();
    ResponseObj addStudent(Student newStudent);
}

package springboot.mongo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;
import springboot.mongo.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public ResponseObj getAllStudent() {
        try {
            return new ResponseObj(200, "Get all success!!!", studentRepository.findAll());
        } catch (Exception e) {
            return  new ResponseObj(500, e.getMessage(), "");
        }
    }

    @Override
    public ResponseObj addStudent(Student newStudent) {
        try {
            studentRepository.save(newStudent);
            return new ResponseObj(200, "Add success !!!", newStudent);
        } catch (Exception e) {
            return  new ResponseObj(500, e.getMessage(), "");
        }
    }
}

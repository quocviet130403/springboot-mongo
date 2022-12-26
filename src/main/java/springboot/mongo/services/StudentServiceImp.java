package springboot.mongo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;
import springboot.mongo.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseObj getStudentByName(String lastName) {
        try {
            Student student = studentRepository.getStudentByLastName(lastName);
            if (student == null) {
                return new ResponseObj(500, "No exits !!!", "");
            }
            return new ResponseObj(200, "success !!!", student);
        } catch (Exception e) {
            return  new ResponseObj(500, e.getMessage(), "");
        }
    }

    @Override
    public ResponseObj updateStudent(Student newStudent, String studentId) {
        try {
            Optional<Student> updateStudent = Optional.of(studentRepository.findById(studentId)
                    .map(student -> {
                        student.setLastName(newStudent.getLastName());
                        student.setFirstName(newStudent.getFirstName());
                        student.setAge(newStudent.getAge());
                        student.setHobbits(newStudent.getHobbits());
                        student.setAddresses(newStudent.getAddresses());
                        return studentRepository.save(student);
                    }).orElseGet(() -> {
                        newStudent.setStudentId(studentId);
                        return studentRepository.save(newStudent);
                    }));
            return new ResponseObj(200, "Update success !!!", newStudent);
        } catch (Exception e) {
            return new ResponseObj(500, e.getMessage(), "");
        }
    }

    @Override
    public ResponseObj deleteStudent(String studentId) {
        try {
            boolean checkStudent = studentRepository.existsById(studentId);
            if (checkStudent) {
                studentRepository.deleteById(studentId);
                return new ResponseObj(200, "Delete Sucess", "");
            } else {
                return new ResponseObj(500, "No exits !!!", "");
            }
        } catch (Exception e) {
            return  new ResponseObj(500, e.getMessage(), "");
        }
    }
}

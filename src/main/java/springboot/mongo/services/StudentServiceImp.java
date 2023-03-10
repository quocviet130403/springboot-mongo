package springboot.mongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;
import springboot.mongo.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
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

    @Override
    public ResponseObj getStudentBetweenAge(Integer minAge, Integer maxAge) {
        try {
            List<Student> student = studentRepository.getStudentBetween(minAge, maxAge);
            if (student.size() > 0) {
                return new ResponseObj(200, "Query Sucess", student);
            } else {
                return new ResponseObj(500, "No exits !!!", "");
            }
        } catch (Exception e) {
            return  new ResponseObj(500, e.getMessage(), "");
        }

    }

    @Override
    public Page<Student> filterStudent(String firstName, Integer minAge, Integer maxAge, String country, Pageable pageable) {
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            criteria.add(Criteria.where("firstName").regex(firstName, "i"));
        }
        if (minAge != null && maxAge != null) {
            criteria.add(Criteria.where("age").gte(minAge).lte(maxAge));
        }
        if (country != null && !country.isEmpty()) {
            criteria.add(Criteria.where("addresses.country").is(country));
        }

        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria()
                    .andOperator(criteria.toArray(new Criteria[0])));
        }

        Page<Student> student = PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Student.class), pageable, () -> mongoTemplate.count(query.skip(0).limit(0), Student.class));

        return student;
    }
}

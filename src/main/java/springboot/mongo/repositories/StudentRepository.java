package springboot.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import springboot.mongo.collections.Student;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student getStudentByLastName(String lastName);
}

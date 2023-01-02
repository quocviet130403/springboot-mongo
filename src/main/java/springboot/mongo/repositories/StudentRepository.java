package springboot.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import springboot.mongo.collections.Student;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student getStudentByLastName(String lastName);
    @Query(value = "{'age' : { $gt : ?0, $lt : ?1 }}", fields = "{addresses:  0}")
    List<Student> getStudentBetween(Integer minAge, Integer maxAge);
}

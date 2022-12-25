package springboot.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import springboot.mongo.collections.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}

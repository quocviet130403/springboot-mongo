package springboot.mongo.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    @Id
    private String studentId;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> hobbits;
    private List<Address> addresses;


}

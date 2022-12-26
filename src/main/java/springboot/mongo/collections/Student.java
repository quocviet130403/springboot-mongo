package springboot.mongo.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    @Id
    private String studentId;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private Integer age;
    @NotEmpty
    private List<String> hobbits;
    @NotEmpty
    private List<Address> addresses;


}

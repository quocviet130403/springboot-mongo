package springboot.mongo.collections;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseObj {
    private Integer status;
    private String messange;
    private Object data;
}

package tech.richard.springbootgraphql.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@Document(collection = "car")
public class CarEntity {
    @Id
    private String id;
    private String name;
    private String color;
    private String year;
    private String locationEntity;

}

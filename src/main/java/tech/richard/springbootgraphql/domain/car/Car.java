package tech.richard.springbootgraphql.domain.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Car implements Serializable {
    @JsonProperty("id")
    private String carId;
    private String name;
    private String color;
    private String year;
    private String location;
}

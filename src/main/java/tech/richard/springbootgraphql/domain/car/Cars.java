package tech.richard.springbootgraphql.domain.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.richard.springbootgraphql.domain.location.Location;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Cars {
    @JsonProperty("id")
    private String carId;
    private String name;
    private String color;
    private String year;
    private String location;
}

package tech.richard.springbootgraphql.domain.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import tech.richard.springbootgraphql.domain.manager.Manager;
import tech.richard.springbootgraphql.domain.car.Car;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Location implements Serializable {
    @JsonProperty("id")
    private String locationId;
    private String city;
    private String state;
    private String zipcode;
    private Manager manager;
    private List<Car> carsList;
}

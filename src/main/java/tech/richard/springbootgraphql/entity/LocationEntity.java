package tech.richard.springbootgraphql.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.richard.springbootgraphql.domain.car.Cars;
import tech.richard.springbootgraphql.domain.manager.Manager;

import java.util.List;

@Data
@Builder(toBuilder = true)
@Document(collection = "location")
public class LocationEntity {
    @Id
    private String id;
    private String city;
    private String state;
    private String zipcode;
    private Manager manager;
    private List<Cars> carsList;
}

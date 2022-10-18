package tech.richard.springbootgraphql.domain.manager;

import lombok.Builder;
import lombok.Data;
import tech.richard.springbootgraphql.domain.location.Location;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
public class Manager implements Serializable {
    private String name;
    private Location location;
}

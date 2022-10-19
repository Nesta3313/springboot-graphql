package tech.richard.springbootgraphql.api.graphql;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CarGraphQLResponse {
    public static final String GQL_TYPE_NAME = "Car";
    String carId;
    String name;
    String color;
    String year;
    String location;
}

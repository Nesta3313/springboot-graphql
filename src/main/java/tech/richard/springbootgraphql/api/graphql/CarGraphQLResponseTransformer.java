package tech.richard.springbootgraphql.api.graphql;

import tech.richard.springbootgraphql.domain.car.Car;

public class CarGraphQLResponseTransformer {
    static CarGraphQLResponse toCarGraphQLResponse(Car car) {
        return CarGraphQLResponse
                .builder()
                .carId(car.getCarId())
                .name(car.getName())
                .color(car.getColor())
                .year(car.getYear())
                .location(car.getLocation())
                .build();
    }
}

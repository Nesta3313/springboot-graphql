package tech.richard.springbootgraphql;

import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.entity.CarEntity;

public class TestObjectFactory {
    public static Car buildCars(String carId) {
        return Car.builder()
                .carId(carId)
                .name("name")
                .color("color")
                .year("year")
                .location("location")
                .build();

    }

    public static CarEntity buildCarEntity(String carId) {
        return CarEntity.builder()
                .id(carId)
                .name("nameEntity")
                .color("colorEntity")
                .year("yearEntity")
                .locationEntity("locationEntity")
                .build();
    }
}

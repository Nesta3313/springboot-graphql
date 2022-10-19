package tech.richard.springbootgraphql.integration;

import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.entity.CarEntity;

public class Transformers {

    public static CarEntity toCarEntity(Car car) {
        return CarEntity.builder()
                .name(car.getName())
                .color(car.getColor())
                .year(car.getYear())
                .locationEntity(car.getLocation())
                .build();
    }

    public static Car toCar(CarEntity carEntity) {
        return Car.builder()
                .carId(carEntity.getId())
                .name(carEntity.getName())
                .color(carEntity.getColor())
                .year(carEntity.getYear())
                .location(carEntity.getLocationEntity())
                .build();
    }
}

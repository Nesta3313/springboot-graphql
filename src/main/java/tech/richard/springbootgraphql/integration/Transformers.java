package tech.richard.springbootgraphql.integration;

import tech.richard.springbootgraphql.domain.car.Cars;
import tech.richard.springbootgraphql.entity.CarEntity;

public class Transformers {

    public static CarEntity toCarEntity(Cars car) {
        return CarEntity.builder()
                .id(car.getCarId())
                .name(car.getName())
                .color(car.getColor())
                .year(car.getYear())
                .locationEntity(car.getLocation())
                .build();
    }

    public static Cars toCar(CarEntity carEntity) {
        return Cars.builder()
                .carId(carEntity.getId())
                .name(carEntity.getName())
                .color(carEntity.getColor())
                .year(carEntity.getYear())
                .location(carEntity.getLocationEntity())
                .build();
    }
}

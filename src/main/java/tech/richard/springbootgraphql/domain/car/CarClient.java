package tech.richard.springbootgraphql.domain.car;

import java.util.List;
import java.util.Optional;
public interface CarClient {
    Car saveCars(Car car);
    Optional<Car> getCarById(String carId);
    List<Car> findAllCars();
    Car findCarByIdAndUpdate(Car car, String carId);
    void deleteCarById(String carId);
}

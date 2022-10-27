package tech.richard.springbootgraphql.domain.car;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarClient {
    Car saveCars(Car car);
    Optional<Car> getCarById(String carId);
    List<Car> findAllCars(Set<String> carIds);
    Car findCarByIdAndUpdate(Car car, String carId);
    String deleteCarById(String carId);
}

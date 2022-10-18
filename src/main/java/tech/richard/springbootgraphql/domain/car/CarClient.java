package tech.richard.springbootgraphql.domain.car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface CarClient {
    Cars saveCars(Cars car);
    Optional<Cars> getCarById(String carId);
    List<Cars> findAllCars();
    Cars findCarByIdAndUpdate(Cars car, String carId);
    void deleteCarById(String carId);
}

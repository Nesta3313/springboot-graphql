package tech.richard.springbootgraphql.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.richard.springbootgraphql.domain.car.CarClient;
import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.entity.CarEntity;
import tech.richard.springbootgraphql.exception.CarNotFoundException;
import tech.richard.springbootgraphql.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tech.richard.springbootgraphql.integration.Transformers.toCar;
import static tech.richard.springbootgraphql.integration.Transformers.toCarEntity;

@Service
public class CarClientImpl implements CarClient {

    CarRepository carRepository;

    public CarClientImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }



    @Override
    public Car saveCars(Car car) {
        return toCar(carRepository.save(toCarEntity(car)));
    }

    @Override
    public Optional<Car> getCarById(String carId) {
        Optional<CarEntity> optionalCar = carRepository.findById(carId);
                if(optionalCar.isPresent()){
                    return optionalCar.map(carEntity ->  toCar(carEntity));
                }else{
                    return Optional.empty();
                }
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carEntity -> toCar(carEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Car findCarByIdAndUpdate(Car car, String carId) {
        Optional<CarEntity> optionalCarEntity = carRepository.findById(carId);

        if(optionalCarEntity.isPresent()){
            CarEntity carEntity = optionalCarEntity.get();
            updateCarEntity(carEntity, car);
            return toCar(carRepository.save(carEntity));
        }else{
            throw new CarNotFoundException(String.format("Car with id %s cannot be found", carId));
        }

    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.deleteById(carId);
    }

    private void updateCarEntity(CarEntity carToUpdate, Car car) {
        carToUpdate.setId(car.getCarId()!= null ? car.getCarId() : carToUpdate.getId());
        carToUpdate.setName(car.getName() != null ? car.getName() : carToUpdate.getName());
        carToUpdate.setColor(car.getColor() != null ? car.getColor() : carToUpdate.getColor());
        carToUpdate.setYear(car.getYear() != null ? car.getYear() : carToUpdate.getYear());
        carToUpdate.setLocationEntity(car.getLocation() != null ? car.getLocation() : carToUpdate.getLocationEntity());
    }
}

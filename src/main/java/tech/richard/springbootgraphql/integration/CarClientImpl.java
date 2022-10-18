package tech.richard.springbootgraphql.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.richard.springbootgraphql.domain.car.CarClient;
import tech.richard.springbootgraphql.domain.car.Cars;
import tech.richard.springbootgraphql.entity.CarEntity;
import tech.richard.springbootgraphql.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CarClientImpl implements CarClient {

    @Autowired
    CarRepository carRepository;



    @Override
    public Cars saveCars(Cars car) {
        return Transformers.toCar(carRepository.save(Transformers.toCarEntity(car)));
    }

    @Override
    public Optional<Cars> getCarById(String carId) {
        return carRepository.findById(carId)
                .map(carEntity ->  Transformers.toCar(carEntity));
    }

    @Override
    public List<Cars> findAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carEntity -> Transformers.toCar(carEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Cars findCarByIdAndUpdate(Cars car, String carId) {
        Optional<CarEntity> optionalCarEntity = carRepository.findById(carId);

        if(optionalCarEntity.isPresent()){
            CarEntity carEntity = optionalCarEntity.get();
            updateCarEntity(carEntity, car);
            return Transformers.toCar(carRepository.save(carEntity));
        }else{
            throw new CarNotFoundException(String.format("Car with id %s cannot be found", carId));
        }

    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.deleteById(carId);
    }

    private void updateCarEntity(CarEntity carToUpdate, Cars car) {
        carToUpdate.setName(car.getName() != null ? car.getName() : carToUpdate.getName());
        carToUpdate.setColor(car.getColor() != null ? car.getColor() : carToUpdate.getColor());
        carToUpdate.setYear(car.getYear() != null ? car.getYear() : carToUpdate.getYear());
        carToUpdate.setLocationEntity(car.getLocation() != null ? car.getLocation() : carToUpdate.getLocationEntity());
    }
}

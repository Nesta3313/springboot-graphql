package tech.richard.springbootgraphql.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.richard.springbootgraphql.TestObjectFactory;
import tech.richard.springbootgraphql.domain.car.Cars;
import tech.richard.springbootgraphql.entity.CarEntity;
import tech.richard.springbootgraphql.repository.CarRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarClientImplTest {
    private CarRepository carRepository;
    private CarClientImpl subject;

    private final String carId = "CAR_ID";


    @BeforeEach
    public void setup() {
        carRepository = mock(CarRepository.class);
        subject = new CarClientImpl(carRepository);
    }

    Cars car = TestObjectFactory.buildCars(carId);


    @Test
    @DisplayName("return saved car when car is saved")
    void returnSavedCar() {

        when(carRepository.save(Transformers.toCarEntity(car))).thenReturn(Transformers.toCarEntity(car));

        Cars expectedCar =subject.saveCars(car);

        assertThat(car).isEqualTo(expectedCar);
    }

    @Test
    @DisplayName("return car when findById is called")
    void returnCarWhenFindByIdIsCalled() {
        when(carRepository.findById(carId)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car)));

        Cars expectedCar = subject.getCarById(carId).get();
        assertThat(car).isEqualTo(expectedCar);

    }

    @Test
    @DisplayName("return list of all car when findAll is called")
    void returnListOfCarsWhenFIndAllIsCalled() {
        when(carRepository.findAll()).thenReturn(List.of(Transformers.toCarEntity(car)));

        List<Cars> carsList = subject.findAllCars();

        assertThat(carsList).isEqualTo(List.of(car));
    }

    @Test
    @DisplayName("return updated car when updateCar is called")
    void returnUpdatedCarWhenUpdateCarIsCalled() {
        CarEntity carEntity = TestObjectFactory.buildCarEntity(carId);

        when(carRepository.findById(carId)).thenReturn(Optional.of(carEntity));

        carEntity.setName(car.getName());
        carEntity.setColor(car.getColor());
        carEntity.setYear(car.getYear());
        carEntity.setLocationEntity(car.getLocation());

        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        Cars savedCar = subject.findCarByIdAndUpdate(car, carId);

        verify(carRepository, times(1)).save(carEntity);

    }

    @Test
    @DisplayName("when deleteById is called car is deleted")
    void whenDeleteByIdIsCalledCarIsDeleted() {
        when(carRepository.findById(carId)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car)));

        assertDoesNotThrow(() -> subject.deleteCarById(carId));
    }

}
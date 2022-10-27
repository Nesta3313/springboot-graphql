package tech.richard.springbootgraphql.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.richard.springbootgraphql.TestObjectFactory;
import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.entity.CarEntity;
import tech.richard.springbootgraphql.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CarClientImplTest {
    private CarRepository carRepository;
    private CarClientImpl subject;

    private final String carId1 = "CAR_ID1";
    private final String carId2 = "CAR_ID2";




    @BeforeEach
    public void setup() {
        carRepository = mock(CarRepository.class);
        subject = new CarClientImpl(carRepository);
    }

    final Car car1 = TestObjectFactory.buildCars(carId1);
    final Car car2 = TestObjectFactory.buildCars(carId2);




    @Test
    @DisplayName("return saved car when car is saved")
    void returnSavedCar() {

        when(carRepository.save(Transformers.toCarEntity(car1))).thenReturn(Transformers.toCarEntity(car1));

        Car expectedCar =subject.saveCars(car1);

        assertThat(car1.getName()).isEqualTo(expectedCar.getName());
    }

    @Test
    @DisplayName("return car when findById is called")
    void returnCarWhenFindByIdIsCalled() {
        when(carRepository.findById(carId1)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car1)));

        Car expectedCar = subject.getCarById(carId1).get();
        assertThat(car1.getCarId()).isSameAs(expectedCar.getCarId());
        assertThat(car1.getName()).isSameAs(expectedCar.getName());

    }

    @Test
    @DisplayName("return list of all car when findAll is called")
    void returnListOfCarsWhenFIndAllIsCalled() {
        Set<String> carIdSet = Set.of(carId1, carId2);

        when(carRepository.findById(carId1)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car1)));
        when(carRepository.findById(carId2)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car2)));


        List<Car> carsList = subject.findAllCars(carIdSet);

        assertThat(carsList.size()).isEqualTo(2);

        verify(carRepository, times(1)).findById(carId1);
        verify(carRepository, times(1)).findById(carId2);
    }

    @Test
    @DisplayName("return updated car when updateCar is called")
    void returnUpdatedCarWhenUpdateCarIsCalled() {
        CarEntity carEntity = TestObjectFactory.buildCarEntity(carId1);

        when(carRepository.findById(carId1)).thenReturn(Optional.of(carEntity));

        carEntity.setName(car1.getName());
        carEntity.setColor(car1.getColor());
        carEntity.setYear(car1.getYear());
        carEntity.setLocationEntity(car1.getLocation());

        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        Car savedCar = subject.findCarByIdAndUpdate(car1, carId1);

        verify(carRepository, times(1)).save(carEntity);

    }

    @Test
    @DisplayName("when deleteById is called car is deleted")
    void whenDeleteByIdIsCalledCarIsDeleted() {
        when(carRepository.findById(carId1)).thenReturn(Optional.ofNullable(Transformers.toCarEntity(car1)));

        assertDoesNotThrow(() -> subject.deleteCarById(carId1));
    }

}
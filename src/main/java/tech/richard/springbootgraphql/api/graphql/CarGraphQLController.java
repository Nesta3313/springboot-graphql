package tech.richard.springbootgraphql.api.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.exception.InvalidIdException;
import tech.richard.springbootgraphql.integration.CarClientImpl;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


import static tech.richard.springbootgraphql.api.graphql.CarGraphQLResponseTransformer.toCarGraphQLResponse;

@Controller
public class CarGraphQLController {
    private final String CAR = "car";
    private final String CARS = "cars";
    private final CarClientImpl carClientImpl;

    Logger logger = Logger.getLogger(CarGraphQLController.class.getName());

    @Autowired
    public CarGraphQLController(CarClientImpl carClientImpl) {
        this.carClientImpl = carClientImpl;
    }

    @MutationMapping
    public CarGraphQLResponse addCar(@Argument String name,
                                     @Argument String color,
                                     @Argument String year,
                                     @Argument String location) {
        Car car = Car.builder()
                .name(name)
                .color(color)
                .year(year)
                .location(location)
                .build();

        logger.info("Calling save car query endpoint");


        return toCarGraphQLResponse(carClientImpl.saveCars(car));
    }

    @QueryMapping(name = CAR)
    public CarGraphQLResponse carQueryById(@Argument String id) {
        if(id.equals("")){
            throw new InvalidIdException(String.format("Not a valid id"));
        }

        logger.info("Calling find car by id query endpoint");

        return toCarGraphQLResponse(carClientImpl.getCarById(id).get());
    }

    @QueryMapping(name = CARS)
    public List<CarGraphQLResponse> findAllCars(@Argument Set<String> carIds) {
        List<CarGraphQLResponse> carGraphQLResponse = carClientImpl.findAllCars(carIds.stream().collect(Collectors.toSet()))
                .stream()
                .map(car -> toCarGraphQLResponse(car)).
                collect(Collectors.toList());
        logger.info("Calling findAllCars query endpoint");

        return carGraphQLResponse;
    }

    @MutationMapping()
    public CarGraphQLResponse updateCar(@Argument String name,
                                        @Argument String color,
                                        @Argument String year,
                                        @Argument String location,
                                        @Argument String carId) {
        Car car = Car.builder()
                .carId(carId)
                .name(name)
                .color(color)
                .year(year)
                .location(location)
                .build();

        Car carResponse = carClientImpl.findCarByIdAndUpdate(car, carId);

        logger.info("Calling update car query endpoint");

        return toCarGraphQLResponse(carResponse);
    }

    @MutationMapping
    // FIXME: 10/27/22 Investigate schema bug on endpoint
    public String deleteCars(@Argument String carId) {


        logger.info("Calling delete car query endpoint");
        carClientImpl.deleteCarById(carId);
        return "Deleted";
    }

}

package tech.richard.springbootgraphql.api.graphql;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.exception.InvalidIdException;
import tech.richard.springbootgraphql.integration.CarClientImpl;

import static tech.richard.springbootgraphql.api.graphql.CarGraphQLResponseTransformer.toCarGraphQLResponse;

@Controller
public class CarGraphQLController {
    private final String CAR = "car";
    private final CarClientImpl carClientImpl;

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

        return toCarGraphQLResponse(carClientImpl.saveCars(car));
    }

    @QueryMapping(name = CAR)
    public CarGraphQLResponse carQueryById(@Argument String id) {
        if(id.equals("")){
            throw new InvalidIdException(String.format("Not a valid id"));
        }
        return toCarGraphQLResponse(carClientImpl.getCarById(id).get());
    }

}

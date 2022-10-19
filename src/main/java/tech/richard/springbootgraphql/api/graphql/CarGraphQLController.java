package tech.richard.springbootgraphql.api.graphql;

import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public CarGraphQLResponse addCar(@Argument Car car, GraphQLContext graphQLContext,
                                     DataFetchingEnvironment env) {
        graphQLContext.put("executionId", env.getExecutionId().toString());

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

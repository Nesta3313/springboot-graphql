package tech.richard.springbootgraphql.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import tech.richard.springbootgraphql.TestObjectFactory;
import tech.richard.springbootgraphql.domain.car.Car;
import tech.richard.springbootgraphql.integration.CarClientImpl;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureGraphQlTester
@ActiveProfiles({"test"})
public class CarGraphQLControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    CarClientImpl carClient;

    private Car car;

    @BeforeEach
    void setup() {
        car = TestObjectFactory.buildCars("carId");
        when(carClient.saveCars(car)).thenReturn(car);
    }


    @Test
    @DisplayName("Returns car with all fields")
    void returnCarWithAllFields() {
        String documentName = "addcar";

        graphQlTester.documentName(documentName)
                .variable("name", "New Post")
                .variable("color", "Yello")
                .variable("year", "2022")
                .variable("location", "Memphis")
                .execute()
                .path("addCar.id").hasValue()
                .path("addCar.name").entity(String.class).isEqualTo("New Post")
                .path("addCar.color").entity(String.class).isEqualTo("Yello")
                .path("addCar.year").entity(String.class).isEqualTo("2022");
    }

}

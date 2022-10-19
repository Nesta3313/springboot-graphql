package tech.richard.springbootgraphql.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import tech.richard.springbootgraphql.domain.car.CarClient;
import tech.richard.springbootgraphql.integration.CarClientImpl;
import tech.richard.springbootgraphql.repository.CarRepository;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"tech.richard.springbootgraphql.repository"})
public class Config {
    @Bean
    public CarClient carClient(CarRepository carRepository){
        return new CarClientImpl(carRepository);
    }
}

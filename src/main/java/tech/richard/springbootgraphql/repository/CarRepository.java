package tech.richard.springbootgraphql.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.richard.springbootgraphql.entity.CarEntity;

@Repository
public interface CarRepository extends MongoRepository<CarEntity, String> {
}

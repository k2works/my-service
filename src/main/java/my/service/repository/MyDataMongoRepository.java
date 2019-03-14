package my.service.repository;

import my.service.model.MyDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, Long> {
}

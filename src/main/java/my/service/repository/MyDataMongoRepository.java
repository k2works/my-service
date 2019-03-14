package my.service.repository;

import my.service.model.MyDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyDataMongoRepository extends MongoRepository<MyDataMongo, Long> {
    public List<MyDataMongo> findByName(String s);
}

package br.com.riotalks.mogodb.outlayer.gateways.repositories.mongodb;

import br.com.riotalks.mogodb.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    @Query("{ 'email' : ?0 }")
    User findMongo(String email);
}
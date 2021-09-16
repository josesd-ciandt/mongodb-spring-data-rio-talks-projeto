package br.com.riotalks.mogodb.outlayer.gateways.repositories.mongodb;

import br.com.riotalks.mogodb.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRepositoryWithMongoTemplate {

    private final MongoTemplate mongoTemplate;

    public User insert(User user) {
        User userCreated = mongoTemplate.insert(user);
        return userCreated;
    }

    public List<User> find(User user) {

        Query query = new Query(new Criteria().orOperator(
                Criteria.where("name").regex(Objects.nonNull(user.getName()) ? user.getName() : ""),
                Criteria.where("email").regex(Objects.nonNull(user.getEmail()) ? user.getEmail() : ""),
                Criteria.where("age").lte(user.getAge()),
                Criteria.where("birthDate").is(user.getBirthDate())));

        return mongoTemplate.find(query, User.class);
    }

    public User update(User user) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));

        User userUpdated = mongoTemplate.update(User.class)
                .matching(query)
                .replaceWith(user)
                .withOptions(FindAndReplaceOptions.options().returnNew())
                .as(User.class)
                .findAndReplaceValue();

        return userUpdated;
    }

    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, User.class);
    }


    public Collection<User> insertAll(List<User> users) {
        Collection<User> userCreated = mongoTemplate.insertAll(users);
        return userCreated;
    }
}
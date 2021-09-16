package br.com.riotalks.mogodb.outlayer.gateways.repositories;

import br.com.riotalks.mogodb.entities.User;
import br.com.riotalks.mogodb.outlayer.gateways.UserGateway;
import br.com.riotalks.mogodb.outlayer.gateways.repositories.mongodb.UserRepository;
import br.com.riotalks.mogodb.outlayer.gateways.repositories.mongodb.UserRepositoryWithMongoTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserRepositoryWithMongoTemplate userRepositoryWithMongoTemplate;

    @Value("${config.flag}")
    private Boolean flag;

    @Override
    public User createUser(User user) {
        try {
            User userInserted;
            if (flag) {
                userInserted = userRepository.insert(user);
            } else {
                userInserted = userRepositoryWithMongoTemplate.insert(user);
            }
            return userInserted;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public User getUser(User user) {
        try {
            if (flag) {
                return userRepository.findMongo(user.getEmail());
            } else {
                return userRepositoryWithMongoTemplate.find(user);
            }
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public User updateUser(User user) {
        try {
            if (flag) {
                return userRepository.insert(user);
            } else {
                return userRepositoryWithMongoTemplate.update(user);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteUser(String id) {
        try {
            if (flag) {
                userRepository.deleteById(id);
            } else {
                userRepositoryWithMongoTemplate.delete(id);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Collection<User> createUsers(List<User> users) {
        try {
            Collection<User> usersInserted;
            if (flag) {
                usersInserted = userRepository.saveAll(users);
            } else {
                usersInserted = userRepositoryWithMongoTemplate.insertAll(users);
            }
            return usersInserted;
        } catch (Exception e) {
            throw e;
        }
    }

}

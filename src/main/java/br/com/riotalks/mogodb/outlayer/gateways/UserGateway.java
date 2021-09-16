package br.com.riotalks.mogodb.outlayer.gateways;

import br.com.riotalks.mogodb.entities.User;

import java.util.Collection;
import java.util.List;

public interface UserGateway {
    User getUser(User user) throws Exception;

    User updateUser(User user);

    User createUser(User user);

    void deleteUser(String id);

    Collection<User> createUsers(List<User> users);
}

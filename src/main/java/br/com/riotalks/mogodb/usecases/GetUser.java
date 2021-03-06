package br.com.riotalks.mogodb.usecases;

import br.com.riotalks.mogodb.entities.User;
import br.com.riotalks.mogodb.outlayer.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUser {

    private final UserGateway userGateway;

    public List<User> execute(User user) throws Exception {
        return userGateway.getUser(user);
    }

}

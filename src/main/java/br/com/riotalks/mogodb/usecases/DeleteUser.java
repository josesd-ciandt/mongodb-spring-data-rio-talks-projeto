package br.com.riotalks.mogodb.usecases;

import br.com.riotalks.mogodb.outlayer.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUser {

    private final UserGateway userGateway;

    public void execute(String id) {
        userGateway.deleteUser(id);
    }

}

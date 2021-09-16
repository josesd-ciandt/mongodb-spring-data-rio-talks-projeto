package br.com.riotalks.mogodb.outlayer.entrypoints;

import br.com.riotalks.mogodb.commons.contants.ControllerConstants;
import br.com.riotalks.mogodb.entities.User;
import br.com.riotalks.mogodb.usecases.*;
import br.com.riotalks.mogodb.utils.FormatDateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "Controler")
public class Controler {

    private final CreateUser createUser;
    private final GetUser getUser;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;
    private final CreateManyUsers createManyUsers;


    @ApiOperation(value = "Recebe usuário e salva no banco")
    @PostMapping(value = ControllerConstants.V1 + ControllerConstants.USER)
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return createUser.execute(user);
    }

    @ApiOperation(value = "Recebe usuários e salva todos no banco")
    @PostMapping(value = ControllerConstants.V1 + ControllerConstants.USER_BULK)
    @ResponseStatus(HttpStatus.CREATED)
    public Collection<User> createManyUsers(@RequestBody List<User> users) {
        return createManyUsers.execute(users);
    }

    @ApiOperation(value = "Recebe um email de usuário e busca o mesmo no banco de dados")
    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    public User getFullUrl(@RequestParam(required = false) String email,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Integer age,
                           @RequestParam(required = false) String dataNascimento) throws Exception {

        return getUser.execute(User.builder()
                .age(age)
                .name(name)
                .email(email)
                .birthDate(FormatDateUtils.formatDate(dataNascimento))
                .build());
    }

    @ApiOperation(value = "Recebe usuário e atualiza no banco")
    @PutMapping(value = ControllerConstants.V1 + ControllerConstants.USER)
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        return updateUser.execute(user);
    }

    @ApiOperation(value = "Recebe usuário e deleta do banco")
    @DeleteMapping(value = ControllerConstants.V1 + ControllerConstants.USER + ControllerConstants.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(value = "userId") final String userId) {
        deleteUser.execute(userId);
    }

}

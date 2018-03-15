package pl.jerckov.user.service.infrastructure;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import pl.jerckov.user.service.application.User;
import pl.jerckov.user.service.application.UserNotFoundExeption;
import pl.jerckov.user.service.application.UserService;
import pl.jerckov.user.service.infrastructure.rest.controller.UserRestController;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ServerSpec {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserRestController restController;

    @Before
    public void setUp() {
        initMocks(this);
        final UUID existUser = UUID.fromString("07f9fde1-c348-4c4c-81db-92953d45745d");
        final UUID notExistUser = UUID.fromString("34930004-16d3-4cc9-a514-414849535bb3");
        given(userService.getUser(existUser)).willReturn(new User(existUser, "Marek"));
        given(userService.getUser(notExistUser)).willThrow(new UserNotFoundExeption(notExistUser));

        final Set<User> users = new HashSet<>();
        users.add(new User(UUID.randomUUID(), "Marek"));
        given(userService.getUsers()).willReturn(users);
        RestAssuredMockMvc.standaloneSetup(restController);
    }
}

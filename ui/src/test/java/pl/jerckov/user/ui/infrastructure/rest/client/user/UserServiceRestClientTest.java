package pl.jerckov.user.ui.infrastructure.rest.client.user;

import static org.assertj.core.api.Assertions.assertThat;
import pl.jerckov.user.ui.infrastructure.rest.client.user.model.UserResponse;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class UserServiceRestClientTest {

    @ClassRule
    public static StubRunnerRule STUBS = new StubRunnerRule().downloadLatestStub("pl.jerckov.user.service",
                                                                                 "user-service",
                                                                                 "stubs");
    private static UserServiceRestClient USER_SERVICE;

    @BeforeClass
    public static void setUp() {
        USER_SERVICE = new UserServiceRestClient(new RestTemplate(),
                                                 STUBS.findStubUrl("pl.jerckov.user.service", "user-service")
                                                      .toString());
    }

    @Test
    public void shouldPostUser() {
        //given
        final UUID id = UUID.fromString("3f19ee87-a3ce-4c78-a4da-c7b977eee219");

        //when
        final ResponseEntity<UserResponse> result = USER_SERVICE.postUser("Jan");

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isEqualToComparingFieldByField(new UserResponse(id, "Jan"));
    }
}

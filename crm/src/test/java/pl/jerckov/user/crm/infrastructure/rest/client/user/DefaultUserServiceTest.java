package pl.jerckov.user.crm.infrastructure.rest.client.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import pl.jerckov.user.crm.infrastructure.rest.client.user.model.UserResponse;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class DefaultUserServiceTest {

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
    public void shouldGetUser() {
        //given
        final UUID id = UUID.fromString("07f9fde1-c348-4c4c-81db-92953d45745d");

        //when
        final ResponseEntity<UserResponse> result = USER_SERVICE.getUser(id);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualToComparingFieldByField(new UserResponse(id, "Jan"));
    }

    @Test
    public void shouldNotFoundUser() {
        //given
        final UUID id = UUID.fromString("34930004-16d3-4cc9-a514-414849535bb3");

        //when
        final Throwable result = catchThrowable(() -> USER_SERVICE.getUser(id));

        //then
        assertThat(result).isInstanceOf(HttpClientErrorException.class);
        assertThat(((HttpClientErrorException) result).getRawStatusCode()).isEqualTo(404);
    }

    @Test
    public void shouldGetUsers() {
        //given

        //when
        final ResponseEntity<UserResponse[]> result = USER_SERVICE.getUsers();

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).hasSize(1);
    }
}

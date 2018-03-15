package pl.jerckov.user.ui.infrastructure.rest.client.user;

import pl.jerckov.user.ui.infrastructure.rest.client.user.model.UserRequest;
import pl.jerckov.user.ui.infrastructure.rest.client.user.model.UserResponse;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class UserServiceRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public UserServiceRestClient(final RestTemplate restTemplate, final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    ResponseEntity<UserResponse> postUser(final String name) {
        return this.restTemplate.exchange(RequestEntity.post(URI.create(baseUrl + "/users"))
                                                       .contentType(MediaType.APPLICATION_JSON)
                                                       .body(new UserRequest(name)), UserResponse.class);
    }
}

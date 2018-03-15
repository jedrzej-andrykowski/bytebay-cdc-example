package pl.jerckov.user.crm.infrastructure.rest.client.user;

import pl.jerckov.user.crm.infrastructure.rest.client.user.model.UserResponse;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

public class UserServiceRestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public UserServiceRestClient(final RestTemplate restTemplate, final String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<UserResponse[]> getUsers() {
        return restTemplate.exchange(RequestEntity.get(URI.create(baseUrl + "/users"))
                                                  .build(), UserResponse[].class);
    }

    public ResponseEntity<UserResponse> getUser(final UUID id) {
        return restTemplate.exchange(RequestEntity.get(URI.create(baseUrl + "/users/" + id))
                                                  .build(), UserResponse.class);
    }
}

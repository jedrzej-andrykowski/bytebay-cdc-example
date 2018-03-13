package pl.jerckov.user.ui.infrastructure.rest.client.user;

import pl.jerckov.user.ui.application.User;
import pl.jerckov.user.ui.application.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class DefaultUserService implements UserService {

    private static final String ITEM_SERVICE_URL = "http://localhost:8080/users";
    private final RestTemplate restTemplate;

    public DefaultUserService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RequestEntity<Void> getBuild() {
        return RequestEntity.get(URI.create(ITEM_SERVICE_URL))
                            .build();
    }

    @Override
    public void addUser(final User user) {
        this.restTemplate.exchange(RequestEntity.post(URI.create(ITEM_SERVICE_URL))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .body(user), User.class);
    }
}

package pl.jerckov.user.crm.infrastructure.rest.client.user;

import pl.jerckov.user.crm.application.User;
import pl.jerckov.user.crm.application.UserService;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultUserService implements UserService {

    private static final String ITEM_SERVICE_URL = "http://localhost:8080/users";
    private final RestTemplate restTemplate;

    public DefaultUserService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllIUsers() {
        return Arrays.asList(exchange("", User[].class));
    }

    @Override
    public User getUser(final UUID id) {
        return exchange("/" + id, User.class);
    }

    private <T> T exchange(final String path, final Class<T> responseBody) {
        return restTemplate.exchange(RequestEntity.get(URI.create(ITEM_SERVICE_URL + path))
                                                  .build(), responseBody)
                           .getBody();
    }
}

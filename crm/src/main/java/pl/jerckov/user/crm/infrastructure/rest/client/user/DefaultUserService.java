package pl.jerckov.user.crm.infrastructure.rest.client.user;

import pl.jerckov.user.crm.application.User;
import pl.jerckov.user.crm.application.UserService;
import pl.jerckov.user.crm.infrastructure.rest.client.user.model.UserResponse;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultUserService implements UserService {

    private final UserServiceRestClient userServiceRestClient;

    public DefaultUserService(final UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public List<User> getAllIUsers() {
        return Arrays.stream(userServiceRestClient.getUsers()
                                                  .getBody())
                     .map(p -> new User(p.getId(), p.getName()))
                     .collect(Collectors.toList());
    }

    @Override
    public User getUser(final UUID id) {
        final UserResponse body = userServiceRestClient.getUser(id)
                                                       .getBody();
        return new User(body.getId(), body.getName());
    }
}

package pl.jerckov.user.ui.infrastructure.rest.client.user;

import pl.jerckov.user.ui.application.User;
import pl.jerckov.user.ui.application.UserService;

import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    private final UserServiceRestClient userServiceRestClient;

    public DefaultUserService(final UserServiceRestClient userServiceRestClient) {
        this.userServiceRestClient = userServiceRestClient;
    }

    @Override
    public void addUser(final User user) {
        userServiceRestClient.postUser(user.getName());
    }
}

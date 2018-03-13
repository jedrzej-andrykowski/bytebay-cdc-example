package pl.jerckov.user.crm.application;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllIUsers();
    User getUser(final UUID id);
}

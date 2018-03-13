package pl.jerckov.user.service.application;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository {
    Optional<User> find(final UUID id);
    Set<User> findAll();
    void save(final User user);
    void remove(final UUID id);
}

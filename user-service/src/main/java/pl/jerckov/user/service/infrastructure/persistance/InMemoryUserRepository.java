package pl.jerckov.user.service.infrastructure.persistance;

import pl.jerckov.user.service.application.User;
import pl.jerckov.user.service.application.UserRepository;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InMemoryUserRepository implements UserRepository {

    private static final Map<UUID, User> users = new LinkedHashMap<>();

    static {
        final UUID first = UUID.fromString("5d55e9c8-4155-4e01-8a4e-fa961339370e");
        final UUID second = UUID.fromString("a66f0f25-d224-4fe0-a77c-32abd64a595a");
        final UUID third = UUID.fromString("8e70c459-0bb5-4425-94dc-f2f7793838f0");
        users.put(first, new User(first, "Jędrzej"));
        users.put(second, new User(second, "Wojciech"));
        users.put(third, new User(third, "Marcin"));
    }

    @Override
    public Optional<User> find(final UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Set<User> findAll() {
        return users.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public void save(final User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void remove(final UUID id) {
        users.remove(id);
    }
}

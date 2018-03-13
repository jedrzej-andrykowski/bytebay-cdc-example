package pl.jerckov.user.service.application;

import java.util.Set;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(final UUID id, final String name) {
        userRepository.save(new User(id, name));
    }

    public void removeUser(final UUID id) {
        userRepository.remove(id);
    }

    public User getUser(final UUID id) {
        return userRepository.find(id)
                             .orElseThrow(() -> new UserNotFoundExeption(id));
    }

    public Set<User> getUsers() {
        return userRepository.findAll();
    }
}

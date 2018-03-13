package pl.jerckov.user.service.application;

import java.util.UUID;

public class UserNotFoundExeption extends RuntimeException {

    public UserNotFoundExeption(final UUID id) {
        super("Item " + id + " not found");
    }
}

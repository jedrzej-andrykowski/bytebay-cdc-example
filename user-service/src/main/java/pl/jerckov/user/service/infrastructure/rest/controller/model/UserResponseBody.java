package pl.jerckov.user.service.infrastructure.rest.controller.model;

import java.util.UUID;

public class UserResponseBody {

    private final UUID id;
    private final String name;

    public UserResponseBody(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

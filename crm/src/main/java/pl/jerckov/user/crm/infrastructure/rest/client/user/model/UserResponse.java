package pl.jerckov.user.crm.infrastructure.rest.client.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserResponse {

    private final UUID id;
    private final String name;

    public UserResponse(@JsonProperty("id") final UUID id, @JsonProperty("name") final String name) {
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

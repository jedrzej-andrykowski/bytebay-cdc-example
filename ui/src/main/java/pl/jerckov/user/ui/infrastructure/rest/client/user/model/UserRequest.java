package pl.jerckov.user.ui.infrastructure.rest.client.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

    private final String name;

    public UserRequest(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package pl.jerckov.user.service.infrastructure.rest.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequestBody {

    private final String name;

    public UserRequestBody(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

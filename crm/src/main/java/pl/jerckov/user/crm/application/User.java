package pl.jerckov.user.crm.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;
import java.util.UUID;

public class User {

    private final UUID id;
    @Size(min = 3, max = 40)
    private final String name;

    public User(@JsonProperty("id") final UUID id,
                @JsonProperty("name") final String name) {
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

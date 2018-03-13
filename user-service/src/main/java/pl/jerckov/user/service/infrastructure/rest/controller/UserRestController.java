package pl.jerckov.user.service.infrastructure.rest.controller;

import pl.jerckov.user.service.application.User;
import pl.jerckov.user.service.application.UserNotFoundExeption;
import pl.jerckov.user.service.application.UserService;
import pl.jerckov.user.service.infrastructure.rest.controller.model.UserRequestBody;
import pl.jerckov.user.service.infrastructure.rest.controller.model.UserResponseBody;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postUser(final @RequestBody UserRequestBody body, final UriComponentsBuilder builder) {
        final UUID id = UUID.randomUUID();
        userService.addUser(id, body.getName());
        return new ResponseEntity<>(new UserResponseBody(id, body.getName()),
                                    locationHeader(id, builder),
                                    HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(usersResponseBody(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity getUser(final @PathVariable UUID id) {
        final User user = userService.getUser(id);
        return new ResponseEntity<>(new UserResponseBody(id, user.getName()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity deleteUser(final @PathVariable UUID id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserNotFoundExeption.class)
    public ResponseEntity userNotFound(final UserNotFoundExeption exeption) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private HttpHeaders locationHeader(final UUID id, final UriComponentsBuilder builder) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/users/{id}")
                                   .buildAndExpand(id)
                                   .toUri());
        return headers;
    }

    private LinkedHashSet<UserResponseBody> usersResponseBody() {
        return userService.getUsers()
                          .stream()
                          .map(p -> new UserResponseBody(p.getId(), p.getName()))
                          .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}

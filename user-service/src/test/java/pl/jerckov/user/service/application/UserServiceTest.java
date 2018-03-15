package pl.jerckov.user.service.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldAddUser() {
        // given
        final UUID id = UUID.randomUUID();
        final String example = "example";
        final User expected = new User(id, example);

        // when
        userService.addUser(id, example);

        // then
        verify(userRepository).save(expected);
    }

    @Test
    public void shouldRemoveUser() {
        // given
        final UUID id = UUID.randomUUID();

        // when
        userService.removeUser(id);

        // then
        verify(userRepository).remove(id);
    }

    @Test
    public void shouldGetExistingUser() {
        // given
        final UUID id = UUID.randomUUID();
        final User expected = new User(id, "example");
        given(userRepository.find(id)).willReturn(Optional.of(expected));

        // when
        final User result = userService.getUser(id);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotExist() {
        // given
        final UUID id = UUID.randomUUID();
        given(userRepository.find(id)).willThrow(new UserNotFoundExeption(id));

        // when
        final Throwable result = catchThrowable(() -> userService.getUser(id));

        // then
        assertThat(result).isInstanceOf(UserNotFoundExeption.class)
                          .hasMessage("User " + id + " not found");
    }

    @Test
    public void shouldGetUsers() {
        // given
        final Set<User> expected = new HashSet<>();
        expected.add(new User(UUID.randomUUID(), "example"));
        given(userRepository.findAll()).willReturn(expected);

        // when
        final Set<User> result = userService.getUsers();

        // then
        assertThat(result).isEqualTo(expected);
    }
}

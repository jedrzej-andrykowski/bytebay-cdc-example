package pl.jerckov.user.ui.infrastructure.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import pl.jerckov.user.ui.application.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

public class UiControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UiController uiController;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(uiController).build();
    }

    @Test
    public void shouldShowHome() throws Exception {
        // given

        // when
        final ResultActions result = mockMvc.perform(get("/"));
        // then
        result.andExpect(status().isOk())
              .andExpect(view().name("home"));
    }

    @Test
    public void shouldAddUser() throws Exception {
        // given
        final UUID id = UUID.randomUUID();
        final String name = "example";

        // when
        final ResultActions result = mockMvc.perform(post("/add").param("name", name));
        // then
        verify(userService).addUser(any());
        result.andExpect(status().is3xxRedirection())
              .andExpect(redirectedUrl("/"));
    }
}

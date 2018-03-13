package pl.jerckov.user.crm.infrastructure.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import pl.jerckov.user.crm.application.User;
import pl.jerckov.user.crm.application.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

public class CrmControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private CrmController crmController;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        initMocks(this);
        mockMvc = standaloneSetup(crmController).build();
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
    public void shouldShowUser() throws Exception {
        // given
        final UUID id = UUID.randomUUID();
        final String name = "example";
        given(userService.getUser(id)).willReturn(new User(id, name));
        // when
        final ResultActions result = mockMvc.perform(get("/user/" + id));
        // then
        result.andExpect(status().isOk())
              .andExpect(view().name("user"))
              .andExpect(model().attribute("user", hasProperty("id", is(id))))
              .andExpect(model().attribute("user", hasProperty("name", is(name))));
    }
}

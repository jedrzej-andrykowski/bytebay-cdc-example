package pl.jerckov.user.ui;

import pl.jerckov.user.ui.infrastructure.rest.client.user.UserServiceRestClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public UserServiceRestClient userService(final RestTemplate restTemplate){
        return new UserServiceRestClient(restTemplate, "http://localhost:8080");
    }
}

package pl.jerckov.user.service;

import pl.jerckov.user.service.application.UserRepository;
import pl.jerckov.user.service.application.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public UserService userService(final UserRepository userRepository) {
        return new UserService(userRepository);
    }
}

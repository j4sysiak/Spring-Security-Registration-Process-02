package pl.jaceksysiak.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.jaceksysiak.security.ActiveUserStore;

@Configuration
public class AppConfig {
    // beans

    @Bean
    public ActiveUserStore activeUserStore() {
        return new ActiveUserStore();
    }

}
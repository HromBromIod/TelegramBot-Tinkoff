package edu.java.bot.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    @Autowired
    ApplicationConfig applicationConfig;

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }
}

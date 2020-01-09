package de.saxsys.playground.auditable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfiguration {
    @Bean
    public GenerateUUIDListener generateUUIDListener() {
        return new GenerateUUIDListener();
    }
}

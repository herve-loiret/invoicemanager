package fr.eurler.invoicemanager.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import fr.eurler.invoicemanager.properties.MongoDBConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    @Bean
    public MongoClient mongo(MongoDBConfigurationProperties mongoDBConfigurationProperties) {
        ConnectionString connectionString = new ConnectionString(mongoDBConfigurationProperties.getUrl());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDBConfigurationProperties mongoDBConfigurationProperties) {
        return new MongoTemplate(mongo(mongoDBConfigurationProperties), mongoDBConfigurationProperties.getDatabase());
    }

}

package fr.eurler.invoicemanager.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    private String mongoDBUrl;
    private String mongoDBDatabase;

    public MongoConfiguration(@Value("${mongodb.url}") String mongoDBUrl,
                              @Value("${mongodb.database}") String mongoDBDatabase) {
        this.mongoDBUrl = mongoDBUrl;
        this.mongoDBDatabase = mongoDBDatabase;
    }

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(mongoDBUrl);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongoDBDatabase);
    }

}

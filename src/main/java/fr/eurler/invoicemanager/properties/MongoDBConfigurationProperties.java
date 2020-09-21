package fr.eurler.invoicemanager.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mongodb")
public class MongoDBConfigurationProperties {

    private String url;

    private String database;
}

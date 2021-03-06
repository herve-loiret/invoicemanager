package fr.eurler.invoicemanager.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aws")
public class AWSConfigurationProperties {

    private String s3InvoicesBucket;
    private String accessKeyId;
    private String secretAccessKey;
    private String s3Region;

}

package fr.eurler.invoicemanager.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AWSConfigurationProperties {

    private String s3InvoicesBucket;

    public AWSConfigurationProperties(@Value("${aws.s3.invoices.bucket}") String s3InvoicesBucket) {
        this.s3InvoicesBucket = s3InvoicesBucket;
    }

}

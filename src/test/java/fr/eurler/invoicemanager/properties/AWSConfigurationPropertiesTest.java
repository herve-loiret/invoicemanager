package fr.eurler.invoicemanager.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class AWSConfigurationPropertiesTest {

    @Autowired
    private AWSConfigurationProperties awsConfigurationProperties;

    @Test
    void should_retrieve_S3InvoicesBucket_property() {
        assertThat(awsConfigurationProperties.getS3InvoicesBucket()).isNotNull();
    }

    @Test
    void should_retrieve_AccessKeyId_property() {
        assertThat(awsConfigurationProperties.getAccessKeyId()).isNotNull();
    }

    @Test
    void should_retrieve_S3Region_property() {
        assertThat(awsConfigurationProperties.getS3Region()).isNotNull();
    }

    @Test
    void should_retrieve_SecretAccessKey_property() {
        assertThat(awsConfigurationProperties.getSecretAccessKey()).isNotNull();
    }
}
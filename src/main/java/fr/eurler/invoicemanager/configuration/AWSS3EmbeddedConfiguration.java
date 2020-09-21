package fr.eurler.invoicemanager.configuration;

import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import io.findify.s3mock.S3Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile("embeddedS3")
@Configuration
public class AWSS3EmbeddedConfiguration {


    public AWSS3EmbeddedConfiguration() {

    }

    @Bean
    public AmazonS3 amazonS3() {
        S3Mock api = S3Mock.create(8001, "/tmp/s3");
        api.start();

        AmazonS3Client client = new AmazonS3Client(new AnonymousAWSCredentials());
        client.setEndpoint("http://127.0.0.1:8001");
        client.createBucket("some-bucket-name");
        return client;
    }
}


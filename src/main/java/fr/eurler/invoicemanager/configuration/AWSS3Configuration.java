package fr.eurler.invoicemanager.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import fr.eurler.invoicemanager.properties.AWSConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!embeddedS3")
@Configuration
public class AWSS3Configuration {

    @Bean
    public AmazonS3 amazonS3(AWSConfigurationProperties awsConfigurationProperties) {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(
                awsConfigurationProperties.getAccessKeyId(),
                awsConfigurationProperties.getSecretAccessKey());
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(awsConfigurationProperties.getS3Region()))
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}


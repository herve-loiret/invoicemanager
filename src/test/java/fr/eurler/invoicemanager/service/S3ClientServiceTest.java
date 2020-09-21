package fr.eurler.invoicemanager.service;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class S3ClientServiceTest {

    @Autowired
    private S3ClientService s3ClientService;

    @Test
    @SneakyThrows
    void should_upload_and_retrieve_and_object() {
        String bucketName = "some-bucket-name";
        String key = "some_key";
        File file = Paths.get("src/test/resources/s3TestFile.txt").toFile();

        s3ClientService.uploadObject(bucketName, key, file);
        InputStream inputStream = s3ClientService.getObject(bucketName, key);
        String text = IOUtils.toString(inputStream, UTF_8.name());

        assertThat(text).isEqualTo("some content");
    }

}
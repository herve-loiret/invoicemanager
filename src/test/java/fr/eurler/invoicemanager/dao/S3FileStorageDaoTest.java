package fr.eurler.invoicemanager.dao;

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
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static fr.eurler.invoicemanager.configuration.AWSS3EmbeddedConfiguration.EMBEDDED_BUCKET_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class S3FileStorageDaoTest {

    @Autowired
    private S3FileStorageDao s3FileStorageDao;

    @Test
    @SneakyThrows
    void should_upload_and_retrieve_and_object() {
        String bucketName = EMBEDDED_BUCKET_NAME;
        String key = "some_key";
        File file = Paths.get("src/test/resources/s3TestFile.txt").toFile();

        s3FileStorageDao.uploadFile(bucketName, key, file);
        InputStream inputStream = s3FileStorageDao.getFile(bucketName, key);
        String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

        assertThat(text).isEqualTo("some content");
    }

}
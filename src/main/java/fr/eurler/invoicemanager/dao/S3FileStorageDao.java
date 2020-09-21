package fr.eurler.invoicemanager.dao;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

@Slf4j
@Component
public class S3FileStorageDao implements FileStorageDao {

    private final AmazonS3 amazonS3;

    public S3FileStorageDao(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadFile(String bucketName, String key, File file) {
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        amazonS3.putObject(putObjectRequest);
    }

    public InputStream getFile(String bucketName, String key) {
        S3Object fullObject = amazonS3.getObject(new GetObjectRequest(bucketName, key));
        return fullObject.getObjectContent();
    }

}

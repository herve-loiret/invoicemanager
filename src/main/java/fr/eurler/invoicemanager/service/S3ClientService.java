package fr.eurler.invoicemanager.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Slf4j
@Service
public class S3ClientService {

    private final AmazonS3 amazonS3;

    public S3ClientService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void uploadObject(String bucketName, String key, File file) {
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        amazonS3.putObject(putObjectRequest);
    }

    public InputStream getObject(String bucketName, String key) {
        S3Object fullObject = amazonS3.getObject(new GetObjectRequest(bucketName, key));
        return fullObject.getObjectContent();
    }

}

package fr.eurler.invoicemanager.service;

import fr.eurler.invoicemanager.properties.AWSConfigurationProperties;
import fr.eurler.invoicemanager.dao.FileStorageDao;
import fr.eurler.invoicemanager.model.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@AllArgsConstructor
public class InvoiceService {

    private AWSConfigurationProperties awsConfigurationProperties;

    private FileStorageDao fileStorageDao;

    public void addInvoice(File invoice) {
        fileStorageDao.uploadFile(
                awsConfigurationProperties.getS3InvoicesBucket(),
                generateUniqueName(invoice),
                invoice);
    }

    private String generateUniqueName(File invoice) {
        return "todo";
    }

    public InputStream getInvoice(String invoiceId) {
        return fileStorageDao.getFile(awsConfigurationProperties.getS3InvoicesBucket(), invoiceId);
    }

    public List<Invoice> getAllInvoices() {
        return asList(Invoice.builder().id("test1").build(), Invoice.builder().id("test2").build());
    }


}

package fr.eurler.invoicemanager.service;

import fr.eurler.invoicemanager.dao.FileStorageDao;
import fr.eurler.invoicemanager.dao.InvoiceDao;
import fr.eurler.invoicemanager.model.Invoice;
import fr.eurler.invoicemanager.properties.AWSConfigurationProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private AWSConfigurationProperties awsConfigurationProperties;

    private FileStorageDao fileStorageDao;

    private InvoiceDao invoiceDao;

    public void addInvoice(Invoice invoice, File invoiceFile) {
        fileStorageDao.uploadFile(
                awsConfigurationProperties.getS3InvoicesBucket(),
                invoice.getId(),
                invoiceFile);
    }

    public InputStream getInvoice(String invoiceId) {
        return fileStorageDao.getFile(awsConfigurationProperties.getS3InvoicesBucket(), invoiceId);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceDao.getAllInvoices();
    }


}

package fr.eurler.invoicemanager.service;

import fr.eurler.invoicemanager.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class InvoiceService {

    @Value("${aws.s3.invoices.bucket}")
    private String bucketName;

    public void addInvoice(File invoice) {
        
    }

    public byte[] getInvoice(String invoiceId) {
        return null;
    }

    public List<Invoice> getAllInvoices() {
        return asList(Invoice.builder().id("test1").build(), Invoice.builder().id("test2").build());
    }


}

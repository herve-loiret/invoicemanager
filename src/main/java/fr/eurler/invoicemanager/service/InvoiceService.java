package fr.eurler.invoicemanager.service;

import fr.eurler.invoicemanager.model.Invoice;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class InvoiceService {


    public void addInvoice(File invoice) {


    }

    public byte[] getInvoice(String invoiceId) {
        return null;
    }

    public List<Invoice> getAllInvoices() {
        return asList(Invoice.builder().id("test1").build(), Invoice.builder().id("test2").build());
    }


}
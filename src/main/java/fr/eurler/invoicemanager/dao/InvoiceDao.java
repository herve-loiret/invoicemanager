package fr.eurler.invoicemanager.dao;

import fr.eurler.invoicemanager.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice getInvoiceById(String id);

    void save(Invoice invoice);

    List<Invoice> getAllInvoices();

}

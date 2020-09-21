package fr.eurler.invoicemanager.dao;

import fr.eurler.invoicemanager.model.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class InvoiceMongoDBDao implements InvoiceDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Invoice getInvoiceById(String id) {
        return mongoTemplate.findById(id, Invoice.class);
    }

    public void save(Invoice invoice) {
        mongoTemplate.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return mongoTemplate.findAll(Invoice.class);
    }
}

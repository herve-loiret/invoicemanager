package fr.eurler.invoicemanager.dao;


import fr.eurler.invoicemanager.model.Invoice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class InvoiceMongoDBDaoTest {

    @Autowired
    private InvoiceMongoDBDao invoiceMongoDBDao;

    @Test
    void should_save_and_retrieve_an_invoice() {
        String invoiceId = "some_id";
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .build();

        invoiceMongoDBDao.save(invoice);

        Invoice test = invoiceMongoDBDao.getInvoiceById(invoiceId);
        assertThat(test.getId()).isEqualTo(invoiceId);
    }

    @Test
    void should_retrieve_the_list_of_all_invoices() {
        String invoiceId = "some_id";
        Invoice invoice = Invoice.builder()
                .id(invoiceId)
                .build();

        invoiceMongoDBDao.save(invoice);

        List<Invoice> invoiceList = invoiceMongoDBDao.getAllInvoices();
        assertThat(invoiceList).contains(invoice);
    }

}
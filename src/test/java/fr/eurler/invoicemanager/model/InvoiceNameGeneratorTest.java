package fr.eurler.invoicemanager.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InvoiceNameGeneratorTest {

    @Test
    void should_generate_unique_name_for_diferent_Invoice() {
        Invoice invoice = Invoice.builder().id("some_id").build();
        Invoice anotherInvoice = Invoice.builder().id("another_id")
                .build();

        String someName = new InvoiceNameGenerator(invoice).generateUniqueName();
        String anotherName = new InvoiceNameGenerator(anotherInvoice).generateUniqueName();

        assertThat(someName).isNotNull();
        assertThat(anotherName).isNotNull();
        assertThat(someName).isNotEqualTo(anotherName);
    }


}
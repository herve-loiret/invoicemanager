package fr.eurler.invoicemanager.model;

import org.apache.commons.codec.digest.DigestUtils;

public class InvoiceNameGenerator {

    private final Invoice invoice;

    public InvoiceNameGenerator(Invoice invoice) {
        this.invoice = invoice;
    }

    public String generateUniqueName() {
        return DigestUtils.md5Hex(invoice.getId());
    }
}

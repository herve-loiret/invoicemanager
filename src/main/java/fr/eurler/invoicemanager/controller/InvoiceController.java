package fr.eurler.invoicemanager.controller;

import fr.eurler.invoicemanager.model.Invoice;
import fr.eurler.invoicemanager.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public void uploadInvoice(@RequestPart(value = "file") final MultipartFile multipartFile) {
        File invoiceFile = convertMultiPartFileToFile(multipartFile);
        invoiceService.addInvoice(invoiceFile);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<ByteArrayResource> getOneInvoice(@PathVariable String invoiceId) {
        final byte[] data = invoiceService.getInvoice(invoiceId);
        final ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + invoiceId + "\"")
                .body(resource);
    }

    @GetMapping
    public List<String> getInvoices() {
        return invoiceService.getAllInvoices().stream()
                .map(Invoice::getId)
                .collect(toList());
    }


    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            log.error("Error converting the multi-part file to file= {}", ex.getMessage());
        }
        return file;
    }
}

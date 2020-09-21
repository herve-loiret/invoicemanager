package fr.eurler.invoicemanager.controller;

import fr.eurler.invoicemanager.controller.dto.InvoiceDto;
import fr.eurler.invoicemanager.model.Invoice;
import fr.eurler.invoicemanager.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
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
import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public void uploadInvoice(@RequestPart(value = "file") MultipartFile multipartFile,
                              @RequestPart(value = "invoiceDto") InvoiceDto invoiceDto) {
        File invoiceFile = convertMultiPartFileToFile(multipartFile);
        Invoice invoice = toInvoice(invoiceDto);
        invoiceService.addInvoice(invoice, invoiceFile);
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InputStreamResource> getOneInvoice(@PathVariable String invoiceId) {
        InputStream data = invoiceService.getInvoice(invoiceId);
        InputStreamResource resource = new InputStreamResource(data);
        return ResponseEntity
                .ok()
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + invoiceId + "\"")
                .body(resource);
    }

    @GetMapping
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAllInvoices().stream()
                .map(this::toInvoice)
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

    private Invoice toInvoice(InvoiceDto invoiceDto) {
        return Invoice.builder()
                .id(invoiceDto.getId())
                .date(invoiceDto.getDate())
                .build();
    }

    private InvoiceDto toInvoice(Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .date(invoice.getDate())
                .build();
    }

}

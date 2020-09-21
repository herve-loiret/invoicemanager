package fr.eurler.invoicemanager.controller;

import fr.eurler.invoicemanager.controller.dto.InvoiceDto;
import fr.eurler.invoicemanager.dao.InvoiceDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;


@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class InvoiceControllerTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    protected int port;

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void should_upload_a_pdf_return_a_status_OK() {
        InvoiceDto someInvoiceDto = InvoiceDto.builder()
                .id("some_id")
                .date(LocalDate.of(2010, 9, 8))
                .build();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", getSamplePdf());
        body.add("invoiceDto", someInvoiceDto);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, generateMultipartHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity(generateInvoicesUrl(), requestEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    private FileSystemResource getSamplePdf() {
        return new FileSystemResource(Paths.get("src/test/resources/somePdf.pdf").toFile());
    }

    private HttpHeaders generateMultipartHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }

    @Test
    void should_retrieve_invoices_list() {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

        List<InvoiceDto> invoiceDtos = restTemplate.exchange(
                generateInvoicesUrl(),
                GET, entity, new ParameterizedTypeReference<List<InvoiceDto>>() {
                })
                .getBody();

        assertThat(invoiceDtos).isNotNull();
    }

    private String generateInvoicesUrl() {
        return "http://localhost:" + port + "/invoices";
    }
}
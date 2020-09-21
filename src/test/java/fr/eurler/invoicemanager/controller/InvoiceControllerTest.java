package fr.eurler.invoicemanager.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;


@ActiveProfiles("embeddedS3")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class InvoiceControllerTest {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    protected int port;

    @Test
    void should_upload_a_pdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FileSystemResource fileToUpload = new FileSystemResource(Paths.get("src/test/resources/somePdf.pdf").toFile());
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileToUpload);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(generateInvoicesUrl(), requestEntity, String.class);

        assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    void should_retrieve_invoices_list() {
        String forEntity = restTemplate.getForEntity(generateInvoicesUrl(), String.class).getBody();

        assertThat(forEntity).contains("test1", "test2");
    }

    private String generateInvoicesUrl() {
        return "http://localhost:" + port + "/invoices";
    }
}
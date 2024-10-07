package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

// module 6
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GETRouteTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testUploadsEndpointReturns200AndJson() {
        // Send GET request to /uploads
        ResponseEntity<String> response = restTemplate.getForEntity("/uploads", String.class);

        // Verify the status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200");

        // Verify the Content-Type header contains 'application/json'
        HttpHeaders headers = response.getHeaders();
        assertTrue(headers.getContentType().includes(MediaType.APPLICATION_JSON), "Expected content type 'application/json'");

        // Optionally, verify that the response body is not null or empty
        String responseBody = response.getBody();
        assertNotNull(responseBody, "Expected non-null response body");
        assertFalse(responseBody.isEmpty(), "Expected non-empty response body");
    }
}

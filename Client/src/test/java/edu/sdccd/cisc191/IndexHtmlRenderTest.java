package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// module 3
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexHtmlRenderTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIndexHtmlRendersAtPort3000() {
        // cssume the index.html is served at the root URL ("/") at port 3000
        ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:3000/", String.class);

        // check that the HTTP status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected status code 200");

    }
}
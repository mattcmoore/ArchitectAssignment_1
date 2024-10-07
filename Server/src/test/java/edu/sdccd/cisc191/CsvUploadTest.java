package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// module 5
public class CsvUploadTest {

    @Test
    public void testCsvUploadFileNotEmpty() throws IOException {
        // simulate a file upload using MockMultipartFile (you can also use a real file in some scenarios)
        String fileContent = "header1,header2,header3\nvalue1,value2,value3";
        MultipartFile csvUpload = new MockMultipartFile(
                "file",                        // file name
                "csvUpload.file",               // original file name
                "text/csv",                     // content type
                fileContent.getBytes(StandardCharsets.UTF_8) // file content in bytes
        );

        // create a BufferedReader for the uploaded file
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(csvUpload.getInputStream(), StandardCharsets.UTF_8)
        );


        String line = reader.readLine();

        // assert that the first line is not null
        assertNotNull(line, "Expected the first line from the CSV file to be not null");
    }
}
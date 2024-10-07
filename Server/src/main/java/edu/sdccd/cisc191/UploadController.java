package edu.sdccd.cisc191;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadController {

    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    @PostMapping("/upload/csv")
    public ResponseEntity<String> csvUpload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        // Check if the file is empty
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        } //        else if (file.getContentType() != "text/csv" || file.getContentType() != "text/csv" || file.getContentType() != "application/vnd.ms-excel") {
//            return new ResponseEntity<>("Invalid file type. Please upload a CSV file.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
//        }


        /*
         * ??MORE VALIDATION NEEDED??
         */

        // Read the .csv as a buffered stream
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            List<String[]> csvData = new ArrayList<>();

            // Read each line of the CSV file
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                csvData.add(columns);
            }

            // Print .csv data
            csvData.forEach(row -> System.out.println(String.join(", ", row)));

            return new ResponseEntity<>("CSV file uploaded and parsed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to parse CSV file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

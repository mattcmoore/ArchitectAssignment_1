package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Entry;
import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Model.Video;
import edu.sdccd.cisc191.Repositories.EntryRepository;
import edu.sdccd.cisc191.Repositories.VideoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CSVDataController {

    @Autowired
    private CSVService csvService;
    @Autowired
    private UploadService uploadService;

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/csv")
    public ResponseEntity<String> csvUpload(@RequestParam("file") MultipartFile file) {
        String status = "";
        // Check if the file is empty
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        /* else if (file.getContentType() != "text/csv" || file.getContentType() != "text/csv" || file.getContentType() != "application/vnd.ms-excel") {
         *    return new ResponseEntity<>("Invalid file type. Please upload a CSV file.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
         * }
         *  * ??MORE VALIDATION NEEDED??
         */

        try{
            csvService.saveCSV(file);
            status = "uploaded";
            return ResponseEntity.ok("File uploaded to database");
        } catch(Exception e) {
            status = "failed";
            return ResponseEntity.internalServerError().body("Upload Unsuccessful");
        } finally{
            try{
                uploadService.saveUpload(file, status);
                return ResponseEntity.ok("Upload tracked");
            } catch(Exception e) {
                return ResponseEntity.internalServerError().body("Upload Not Tracked");
            }
        }
    }
}

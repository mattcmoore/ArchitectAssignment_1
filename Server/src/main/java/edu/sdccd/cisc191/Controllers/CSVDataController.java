package edu.sdccd.cisc191.Controllers;

import edu.sdccd.cisc191.Services.CSVService;
import edu.sdccd.cisc191.Services.RBTService;
import edu.sdccd.cisc191.Services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class CSVDataController {

    @Autowired
    private CSVService csvService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private RBTService rbtService;

    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    @PostMapping("/upload/csv")
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
            } finally {
                rbtService.populateFileSystem();
            }
        }
    }
}

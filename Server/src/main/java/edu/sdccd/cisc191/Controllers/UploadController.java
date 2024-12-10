package edu.sdccd.cisc191.Controllers;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import edu.sdccd.cisc191.Services.RBTService;
import edu.sdccd.cisc191.Services.UploadListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UploadController {

    @Autowired
    UploadRepository uploadRepository;

    @Autowired
    RBTService rbtService;

    @Autowired
    UploadListService uploadListService;

    boolean isReverse = false;

    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    @GetMapping("/uploads")
    public List<Upload> getJson() {
        System.out.println(isReverse);
        try {
            return uploadListService.createUploadList(isReverse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    @PostMapping("/upload/reverse")
    public ResponseEntity<Boolean> toggleReverse(@RequestBody Map<String, Boolean> request) {
        isReverse = !(request.get("isReverse"));
        return ResponseEntity.status(HttpStatus.OK).body(isReverse);
    }
}



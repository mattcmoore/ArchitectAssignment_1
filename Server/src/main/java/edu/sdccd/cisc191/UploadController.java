package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UploadController {
    public static void createDemoUploadList() {
//        new Upload("file1.csv",LocalDateTime.now(),15,"uploaded").add();
//        new Upload("file2.csv",LocalDateTime.now(),25,"failed").add();
//        new Upload("file3.csv",LocalDateTime.now(),15,"pending").add();
//        new Upload("file4.csv",LocalDateTime.now(),35,"uploaded").add();
//        new Upload("file5.csv",LocalDateTime.now(),15,"uploaded").add();
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/uploads")
    public ArrayList<Upload> getJson() {
        createDemoUploadList();
        return Upload.getList();
    }
}



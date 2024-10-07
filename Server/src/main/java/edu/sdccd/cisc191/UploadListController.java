package edu.sdccd.cisc191;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class UploadListController {
    public static void createDemoUploadList() {
        new Upload(1,"file1.csv",1727987615,15,1).add();
        new Upload(2,"file2.csv",1727987615,25,1).add();
        new Upload(3,"file3.csv",1727987615,15,1).add();
        new Upload(4,"file4.csv",1727987615,35,0).add();
        new Upload(5,"file5.csv",1727987615,15,1).add();
    }

    @CrossOrigin(origins = "http://127.0.0.1:3000/")
    @GetMapping("/uploads")
    public ArrayList<Upload> getJson() {
        createDemoUploadList();
        return Upload.getList();
    }
}



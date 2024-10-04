package edu.sdccd.cisc191;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
      SpringApplication.run(ServerApplication.class, args);
        UploadListController.createDemoUploadList();
        System.out.println(Upload.getList());
    }

}
package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.EntryRepository;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ServerApplication {

    @Autowired
    public Server inMemoryH2DatabaseServer;


    public static void main(String[] args) {
      SpringApplication.run(ServerApplication.class, args);

    }

}

package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class RBTService {

    @Autowired
    public UploadRepository userRepository;

    @Autowired
    RedBlackFileSystem filesystem = new RedBlackFileSystem();


    @EventListener
    public void onDatabaseSeeded(DatabaseSeeder.DatabaseSeededEvent event) {
        // Once the database is seeded, run populateFileSystem
        populateFileSystem();
    }

    @PostConstruct
    public void populateFileSystem() {
        System.out.println("filesystem inserted");
        userRepository.findAll().forEach(upload -> {
            filesystem.insert(upload);
        });
    }
}
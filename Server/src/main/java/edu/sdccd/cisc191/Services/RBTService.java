package edu.sdccd.cisc191.Services;

import edu.sdccd.cisc191.DatabaseSeeder;
import edu.sdccd.cisc191.RedBlackFileSystem;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class RBTService {

    @Autowired
    public UploadRepository uploadRepository;

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
        filesystem.clearFileSystem();
        uploadRepository.findAll().forEach(upload -> {
            filesystem.insert(upload);
        });
    }
}
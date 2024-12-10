package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private UploadRepository uploadRepository;

    public class DatabaseSeededEvent extends ApplicationEvent {
        public DatabaseSeededEvent(Object source) {
            super(source);
        }
    }

    @Bean
    public ApplicationRunner seedDatabase(UploadRepository uploadRepository,  ApplicationEventPublisher publisher) {
        return args -> {
            System.out.println("Seeding database...");
            char[] letters = "JIHGFEDCBA".toCharArray(); // Letters J - A
            Random random = new Random();
            LocalDateTime startDate = LocalDateTime.of(2024, Month.NOVEMBER, 1, 0, 0);

            // Insert 10 entries
            IntStream.range(0, 10).forEach(i -> {
                String name = "Sample_File_" + letters[i];
                LocalDateTime timestamp = startDate.plusDays(i).plusHours(random.nextInt(24)).plusMinutes(random.nextInt(60));
                Long size = 400L + random.nextInt(201); // Random size between 400 and 600
                String status = "failed";

                Upload seedUpload = new Upload();
                seedUpload.setName(name);
                seedUpload.setSize(size);
                seedUpload.setTimestamp(timestamp);
                seedUpload.setStatus(status);
                uploadRepository.save(seedUpload);
            });
            publisher.publishEvent(new DatabaseSeededEvent(this));
        };
    }
}


package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;

import edu.sdccd.cisc191.Model.Entry;
import edu.sdccd.cisc191.Model.Video;
import org.h2.tools.Server;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.sdccd.cisc191.Repositories.EntryRepository;
import edu.sdccd.cisc191.Repositories.VideoRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
public class DBUploadTest {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public DBConfig inMemoryH2DatabaseServer;

    @Test
    @ContextConfiguration(classes = {DBConfig.class})
    public void testDatabaseQueryForVideoAndEntries() {
        // Seed the database with a test entry
        int year = 2024;
        String channel = "testChannel";
        String provider = "testProvider";
        int location = 10; // Leading zeroes are not allowed in integers
        String dateString = "8/25/2015";
        String title = "testVideo";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        Video video = videoRepository.findByTitle(title)
                .orElseGet(() -> {
                    Video newVideo = new Video();
                    newVideo.setTitle(title);
                    newVideo.setYear(year);
                    newVideo.setChannel(channel);
                    return videoRepository.save(newVideo);
                });

        Entry entry = new Entry();
        entry.setProvider(provider);
        entry.setLocation(location);
        entry.setDate(date);
        entry.setVideo(video);

        entryRepository.save(entry);

        // Define the query to join Entries and Videos
        String sql = "SELECT * FROM Entries JOIN Videos ON Entries.video_id = Videos.id WHERE Videos.title = :title";
        Query query = entityManager.createNativeQuery(sql, "EntryVideoMapping"); // Assuming a mapping exists for the result set
        query.setParameter("title", "testVideo");

        List<?> result = query.getResultList();

        // Assert that at least one record exists
        assertFalse(result.isEmpty(), "No records found for the video with title 'testVideo'.");

        // Cleanup the test data
        entryRepository.delete(entry);
        videoRepository.delete(video);
    }
}

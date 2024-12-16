package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.RedBlackFileSystem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InOrderTest {

    private RedBlackFileSystem testFileSystem = new RedBlackFileSystem();

    @Test
    public void testInorder() {
        // Create Upload objects
        Upload uploadOne = new Upload();
        Upload uploadTwo = new Upload();
        Upload uploadThree = new Upload();

        // Set name, timestamp, size, and status for each object
        uploadOne.setName("testUploadOne");
        uploadTwo.setName("testUploadTwo");
        uploadThree.setName("testUpload3");

        LocalDateTime timestampOne = LocalDateTime.of(2024, 12, 1, 10, 0);
        LocalDateTime timestampTwo = LocalDateTime.of(2024, 12, 5, 10, 0);
        LocalDateTime timestampThree = LocalDateTime.of(2024, 12, 10, 10, 0);

        uploadOne.setTimestamp(timestampOne);
        uploadTwo.setTimestamp(timestampTwo);
        uploadThree.setTimestamp(timestampThree);

        uploadOne.setSize(500);
        uploadTwo.setSize(500);
        uploadThree.setSize(500);

        uploadOne.setStatus("test");
        uploadTwo.setStatus("test");
        uploadThree.setStatus("test");

        // Insert uploads into the RedBlackFileSystem
        testFileSystem.insert(uploadOne);
        testFileSystem.insert(uploadTwo);
        testFileSystem.insert(uploadThree);

        // Retrieve the list in order
        List<Upload> uploadList = testFileSystem.listInOrder();

        // Assert timestamps are in order
        for (int i = 1; i < uploadList.size(); i++) {
            assertTrue(uploadList.get(i).getTimestamp().isAfter(uploadList.get(i - 1).getTimestamp()),
                    "Timestamps are not in order");
        }
        testFileSystem.clearFileSystem();
    }
}


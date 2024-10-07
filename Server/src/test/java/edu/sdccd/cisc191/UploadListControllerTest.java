package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

// module 4
@SpringBootTest
public class UploadListControllerTest {

    @Autowired
    private UploadListController uploadListController;

    @Test
    public void testGetJsonReturnsArrayListOfUpload() {
        // creates the demo upload list
        UploadListController.createDemoUploadList();

        // Call the getJson() method
        List<Upload> result = uploadListController.getJson();

        // Verify the result is not null
        assertNotNull(result, "Expected non-null result from getJson()");

        // Verify the result is an ArrayList
        assertTrue(result instanceof ArrayList, "Expected result to be an instance of ArrayList");

        // Verify the result contains Upload objects
        if (!result.isEmpty()) {
            assertTrue(result.get(0) instanceof Upload, "Expected the elements of the result to be of type Upload");
        }
    }
}
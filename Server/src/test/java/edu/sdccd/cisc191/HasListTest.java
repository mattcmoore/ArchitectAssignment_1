package edu.sdccd.cisc191;

import static org.junit.jupiter.api.Assertions.*;

import edu.sdccd.cisc191.Model.Upload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

// module 4
@SpringBootTest
public class HasListTest {

    @Autowired
    private UploadController uploadListController;

    @Test
    public void testGetJsonReturnsArrayListOfUpload() {
        // creates the demo upload list
        UploadController.createDemoUploadList();

        // Call the getJson() method
        List<Upload> result = uploadListController.getJson();

        // Verify the result is an ArrayList
        assertTrue(result instanceof ArrayList, "Expected result to be an instance of ArrayList");
    }
}
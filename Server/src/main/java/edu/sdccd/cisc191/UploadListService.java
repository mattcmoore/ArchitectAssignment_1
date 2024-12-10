package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UploadListService {

    @Autowired
    private UploadRepository userRepository;

    @Autowired
    private RedBlackFileSystem filesystem;

    @Autowired
    private RBTService rbtService;

    public List<Upload> createUploadList(Boolean isReverse) {
        if(isReverse) {
            return filesystem.listReverse();
        } else {
            return filesystem.listInOrder();
        }
    }
}

package edu.sdccd.cisc191;

import org.springframework.stereotype.Service;

@Service
public class CSLMFileSystem {
//    private final FileTableRepository repository;

//    public CSLMFileSystem(FileTableRepository repository) {
//        this.repository = repository;
//    }
//
//    public ConcurrentSkipListMap<Long, FileNode> populateFileNodeMap() {
//        ConcurrentSkipListMap<Long, FileNode> fileNodeMap = new ConcurrentSkipListMap<>();

        // fetchs all rows from the table
        // LAMBDA, how do I access that database? Do I have to make a get request?
//        repository.findAll().forEach(entity -> {
//            // create FileNode from each table row
//            FileNode fileNode = new FileNode(entity.getId(), entity.getName());
//
//            // adds FileNode to the map using the ID as the key
//            fileNodeMap.put(fileNode.getId(), fileNode);
//        });
//
//        return fileNodeMap;
//    }
}

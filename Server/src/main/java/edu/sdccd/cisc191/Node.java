package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;

import java.time.LocalDateTime;

class Node {
    Upload upload;
    Node left, right, parent;
    boolean isRed;

    public Node(Upload upload) {
        this.upload = upload;
        this.isRed = true; // New nodes are red by default
    }
}

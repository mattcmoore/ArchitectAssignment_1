package edu.sdccd.cisc191;

import java.time.LocalDateTime;

class FileNode {
    String filename;
    LocalDateTime timestamp;
    int size;
    String status;
    FileNode left, right, parent;
    boolean isRed;

    public FileNode(String filename, LocalDateTime timestamp, int size, String status) {
        this.filename = filename;
        this.timestamp = timestamp;
        this.size = size;
        this.status = status;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.isRed = true; // default is red
    }

    @Override
    public String toString() {
        return "FileNode{" +
                "filename='" + filename + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}
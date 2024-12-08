package edu.sdccd.cisc191;

import java.time.LocalDateTime;

public class BSTFileSystem {
    private FileNode root;

    // Inserts a new file into the BST
    public void insert(String filename, LocalDateTime timestamp, int size, String status) {
        root = insert(root, filename, timestamp, size, status);
    }

    private FileNode insert(FileNode current, String filename, LocalDateTime timestamp, int size, String status) {
        if (current == null) {
            return new FileNode(filename, timestamp, size, status);
        }

        if (filename.compareTo(current.filename) < 0) {
            current.left = insert(current.left, filename, timestamp, size, status);
        } else if (filename.compareTo(current.filename) > 0) {
            current.right = insert(current.right, filename, timestamp, size, status);
        } else {
            System.out.println("File doesn't exist");
        }

        return current;
    }

    public FileNode search(String filename) {
        return search(root, filename);
    }

    private FileNode search(FileNode current, String filename) {
        if (current == null || filename.equals(current.filename)) {
            return current;
        }

        if (filename.compareTo(current.filename) < 0) {
            return search(current.left, filename);
        } else {
            return search(current.right, filename);
        }
    }

    public void displayInOrder() {
        displayInOrder(root);
    }

    private void displayInOrder(FileNode node) {
        if (node != null) {
            displayInOrder(node.left);
            System.out.println(node);
            displayInOrder(node.right);
        }
    }

    public void displayReverse() {
        displayReverse(root);
    }
    private void displayReverse(FileNode node) {
        if (node != null) {
            displayReverse(node.right);
            System.out.println(node);
            displayReverse(node.left);
        }
    }

}

// self balancing tree, iteration while loop vs. recursive
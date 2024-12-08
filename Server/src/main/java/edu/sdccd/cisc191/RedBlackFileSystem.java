package edu.sdccd.cisc191;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class RedBlackFileSystem {
    private FileNode root;
    private final ReentrantLock lock = new ReentrantLock();

    public RedBlackFileSystem() {
        this.root = null;
    }

    // Insert a node
    public void insert(String filename, LocalDateTime timestamp, int size, String status) {
        lock.lock();
        try {
            FileNode newNode = new FileNode(filename, timestamp, size, status);
            root = insert(root, newNode);
            rebalance(newNode);
        } finally {
            lock.unlock();
        }
    }

    private FileNode insert(FileNode current, FileNode newNode ) {
        if (current == null) {
            return newNode;
        }

        if (newNode.filename.compareTo(current.filename) < 0) {
            current.left = insert(current.left, newNode);
        } else if (newNode.filename.compareTo(current.filename) > 0) {
            current.right = insert(current.right, newNode);
        } else {
            System.out.println("File doesn't exist");
        }

        return current;
    }

    // Rebalance according to red-black properties
    private void rebalance(FileNode node) {
        while (node != root && node.parent.isRed) {
            FileNode parent = node.parent;
            FileNode grandParent = parent.parent;

            // Case A: Parent is left child of grandparent
            if (parent == grandParent.left) {
                FileNode uncle = grandParent.right;

                // Case 1: Uncle is red -> Recolor
                if (uncle != null && uncle.isRed) {
                    grandParent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    // Case 2: Node is right child -> Rotate Left
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    // Case 3: Node is left child -> Rotate Right
                    rotateRight(grandParent);
                    boolean tempColor = parent.isRed;
                    parent.isRed = grandParent.isRed;
                    grandParent.isRed = tempColor;
                    node = parent;
                }
            } else {
                // Case B: Parent is right child of grandparent
                FileNode uncle = grandParent.left;

                // Case 1: Uncle is red -> Recolor
                if (uncle != null && uncle.isRed) {
                    grandParent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandParent;
                } else {
                    // Case 2: Node is left child -> Rotate Right
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }

                    // Case 3: Node is right child -> Rotate Left
                    rotateLeft(grandParent);
                    boolean tempColor = parent.isRed;
                    parent.isRed = grandParent.isRed;
                    grandParent.isRed = tempColor;
                    node = parent;
                }
            }
        }

        root.isRed = false; // Root is always black
    }

    // Rotate left
    private void rotateLeft(FileNode node) {
        FileNode rightChild = node.right;
        node.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    // Rotate right
    private void rotateRight(FileNode node) {
        FileNode leftChild = node.left;
        node.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
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

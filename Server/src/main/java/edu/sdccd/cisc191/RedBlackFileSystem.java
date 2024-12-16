package edu.sdccd.cisc191;

import edu.sdccd.cisc191.Model.Upload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class RedBlackFileSystem {

    private Node root;

    // Insert a value into the Red-Black Tree
    public void insert(Upload upload) {
        Node newNode = new Node(upload);
        root = insert(root, newNode);
        rebalance(newNode);
        System.out.println("node inserted");
    }

    // Recursive insertion
    private Node insert(Node current, Node newNode) {
        if (current == null) {
            return newNode;
        }

/*
 *
 *choose comparison type
 *
 */

        if (newNode.upload.getTimestamp().isBefore(current.upload.getTimestamp())) {
            current.left = insert(current.left, newNode);
            current.left.parent = current;
        } else if (newNode.upload.getTimestamp().isAfter(current.upload.getTimestamp())) {
            current.right = insert(current.right, newNode);
            current.right.parent = current;
        }

        return current;
    }

    public List<Upload> listInOrder()  {
//        List<Upload> uploadList = Collections.synchronizedList(new ArrayList<>());
        List<Upload> uploadList = new ArrayList<>();
        inOrderHelper(root, uploadList);
        return uploadList;
    }


    public List<Upload> listReverse() {
//        List<Upload> uploadList = Collections.synchronizedList(new ArrayList<>());
        List<Upload> uploadList = new ArrayList<>();
// make not recursive
        reverseInOrderHelper(root, uploadList);
        return uploadList;
    }

    // Fix Red-Black Tree violations after insertion
    private void rebalance(Node node) {
        while (node != root && node.parent.isRed) {
            Node grandparent = node.parent.parent;
            Node uncle = (node.parent == grandparent.left) ? grandparent.right : grandparent.left;

            if (uncle != null && uncle.isRed) {
                // Case 1: Uncle is red
                node.parent.isRed = false;
                uncle.isRed = false;
                grandparent.isRed = true;
                node = grandparent;
            } else {
                if (node == node.parent.right && node.parent == grandparent.left) {
                    // Case 2: Left-Right rotation
                    node = node.parent;
                    rotateLeft(node);
                } else if (node == node.parent.left && node.parent == grandparent.right) {
                    // Case 2: Right-Left rotation
                    node = node.parent;
                    rotateRight(node);
                }

                // Case 3: Recolor and rotate
                node.parent.isRed = false;
                grandparent.isRed = true;
                if (node == node.parent.left) {
                    rotateRight(grandparent);
                } else {
                    rotateLeft(grandparent);
                }
            }
        }
        root.isRed = false; // Root is always black
    }

    // Helper: Rotate left
    private void rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if (temp.left != null) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    // Helper: Rotate right
    private void rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if (temp.right != null) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }

    // Helper: In-order traversal
    private void inOrderHelper(Node node, List<Upload> uploadList) {
//        ExecutorService executor = Executors.newFixedThreadPool(4);
        if (node != null) {
//            executor.submit( () -> inOrderHelper(node.left,uploadList) );
//            executor.submit( () -> uploadList.add(node.upload) );
//            executor.submit( () -> inOrderHelper(node.right,uploadList) );
            inOrderHelper(node.left,uploadList);
            uploadList.add(node.upload);
            inOrderHelper(node.right,uploadList);

        }

//        executor.shutdown();
    }

    // Helper: Reverse in-order traversal
    private void reverseInOrderHelper(Node node, List<Upload> uploadList) {
//        ExecutorService executor = Executors.newFixedThreadPool(4);
        if (node != null) {
//            executor.submit( () -> inOrderHelper(node.right,uploadList) );
//            executor.submit( () -> uploadList.add(node.upload) );
//            executor.submit( () -> inOrderHelper(node.left,uploadList) );
            reverseInOrderHelper(node.right,uploadList);
            uploadList.add(node.upload);
            reverseInOrderHelper(node.left,uploadList);
        }

//        executor.shutdown();
    }

    private void clear(Node node) {
        if (node == null) {
            return;
        }
//
//        clear(node.left);
//        clear(node.right);
//
//        if (root == null) {
//            return;
//        }

        // Simulated call - stack
        Stack<Node> fakeCallStack = new Stack<>();
        fakeCallStack.push(root);

        while (!fakeCallStack.isEmpty()) {
            // Pop a node from the stack
            Node current = fakeCallStack.pop();

            // Push the child nodes onto the stack if they exist
            if (current.left != null) {
                fakeCallStack.push(current.left);
            }
            if (current.right != null) {
                fakeCallStack.push(current.right);
            }

            // In Java, garbage collection will handle orphaned nodes
            node.left = null;
            node.right = null;
            node.parent = null;
        }
    }

    // THIS CLEARS THE TREE FROM ROOT TO NULL BOTTOM NODES
    public void clearFileSystem() {
        clear(root); // Start clearing from the root
        root = null;     // Set the root to null after clearing the tree
    }
}

package edu.sdccd.cisc191.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name="UPLOADS")
public class Upload {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="timestamp")
    private LocalDateTime timestamp;
    @Column(name="size")
    private long size;
    @Column(name="status")
    private String status;

    public Upload() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * uploadList stuff
     */

    private static ArrayList<Upload> uploadList = new ArrayList<>();

    public void add() {
        uploadList.add(this);
    }

    public static ArrayList<Upload> getList(){
        return uploadList;
    }

    public static void clearList(){
        uploadList = new ArrayList<Upload>();
    }
}


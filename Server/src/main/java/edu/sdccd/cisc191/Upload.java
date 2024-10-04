package edu.sdccd.cisc191;

import java.util.ArrayList;

public class Upload {
    private final int id;
    private final String name;
    private final int timestamp;
    private final int size;
    private final int status;
    // I HAVE A STATIC ATTRIBUTE
    private static ArrayList<Upload> uploadList = new ArrayList<>();

    public Upload(int id, String name, int timestamp, int size, int status) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.size = size;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStatus() {
        return this.status;
    }

    public int getSize() {
        return this.size;
    }

    public int getTimestamp(){
        return this.timestamp;
    }

    public void add() {
        uploadList.add(this);
    }

    public static ArrayList<Upload> getList(){
        return uploadList;
    }
}


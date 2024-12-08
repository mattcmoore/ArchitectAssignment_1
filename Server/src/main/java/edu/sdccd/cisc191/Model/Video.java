package edu.sdccd.cisc191.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="VIDEOS")
public class Video {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="release_year")
    private int release_year;

    @Column(name="channel")
    private String channel;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entry> entries;

    public Video(){

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return release_year;
    }

    public void setYear(int release_year) {
        this.release_year = release_year;
    }


    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}

package edu.sdccd.cisc191.Services;

import edu.sdccd.cisc191.Model.Entry;
import edu.sdccd.cisc191.Model.Video;
import edu.sdccd.cisc191.Repositories.EntryRepository;
import edu.sdccd.cisc191.Repositories.VideoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    VideoRepository videoRepository;

    public void saveCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            List<Entry> entries = new ArrayList<>();

            for (CSVRecord record : csvParser) {
                int year = Integer.parseInt(record.get("Year"));
                String channel = record.get("Channel");
                String provider = record.get("Provider");
                int location = Integer.parseInt(record.get("Location"));
                String dateString = record.get("Date");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                LocalDate date = LocalDate.parse(dateString, formatter);

                String title = record.get("Title");
                Video video = videoRepository.findByTitle("Title")
                        .orElseGet(() -> { // creates a VIDEOS entry on-the-fly
                            Video newVideo = new Video();
                            newVideo.setTitle(title);
                            newVideo.setYear(year);
                            newVideo.setChannel(channel);
                            return videoRepository.save(newVideo);
                        });

                Entry entry = new Entry();
                entry.setProvider(provider);
                entry.setLocation(location);
                entry.setDate(date);
                entry.setVideo(video);

                entries.add(entry);
            }
            entryRepository.saveAll(entries);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}

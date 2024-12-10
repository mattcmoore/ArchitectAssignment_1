package edu.sdccd.cisc191.Services;

import edu.sdccd.cisc191.Model.Upload;
import edu.sdccd.cisc191.Repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UploadService {

    @Autowired
    UploadRepository uploadRepository;

    public Upload saveUpload(MultipartFile file, String status) {
        String name = file.getOriginalFilename();
        long size = file.getSize();
        LocalDateTime timestamp = LocalDateTime.now();
        Upload newUpload = new Upload();
        newUpload.setName(name);
        newUpload.setSize(size);
        newUpload.setTimestamp(timestamp);
        newUpload.setStatus(status);

        return uploadRepository.save(newUpload);
    }
}

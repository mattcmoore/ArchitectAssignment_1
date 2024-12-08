package edu.sdccd.cisc191.Repositories;

import edu.sdccd.cisc191.Model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.stream.Stream;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
    Stream<Upload> findAllBy();
}
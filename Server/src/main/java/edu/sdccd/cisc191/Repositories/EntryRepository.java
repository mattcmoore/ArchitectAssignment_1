package edu.sdccd.cisc191.Repositories;

import edu.sdccd.cisc191.Model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
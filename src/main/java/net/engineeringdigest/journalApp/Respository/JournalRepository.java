package net.engineeringdigest.journalApp.Respository;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends MongoRepository<JournalEntity,Object> {
    JournalEntity findByTitle(String title);
}

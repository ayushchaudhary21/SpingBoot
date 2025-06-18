package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JournalService {
    JournalEntity saveAllJournal(JournalEntity journalEntity,String userName);
    List<JournalEntity> getAllJournal(String userName);
    JournalEntity getByID(ObjectId id);
    JournalEntity getbyTitle(String title);
    void deleteId(ObjectId id,String userName);
     JournalEntity updateJournalBody(ObjectId id,JournalEntity journalEntity);
}

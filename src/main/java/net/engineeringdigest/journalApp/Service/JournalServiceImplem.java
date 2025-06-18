package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Respository.JournalRepository;
import net.engineeringdigest.journalApp.Respository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImplem implements  JournalService{
    private static final Logger log = LoggerFactory.getLogger(JournalServiceImplem.class);
    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public JournalEntity saveAllJournal(JournalEntity journalEntity,String userName){
        try{
            Optional<User> userDb=userRepository.findByUserName(userName);

            journalEntity.setDate(LocalDateTime.now());
        JournalEntity journalEntity1=journalRepository.save(journalEntity);
            if(userDb.isPresent())
            {
                User user=userDb.get();
                user.getJournalEntityList().add(journalEntity1);
                userRepository.save(user);
            }
            return journalEntity1;

        } catch (Exception e)
        {
           log.error("Exception "+e);
           return null;
        }
    }
    @Override
    public List<JournalEntity> getAllJournal(String userName)
    {
//         return journalRepository.findAll();
      Optional<User> userDb= userRepository.findByUserName(userName);
        if(userDb.isPresent())
        {
             User user=userDb.get();
              return user.getJournalEntityList();
        }
        return null;
    }

    @Override
    public JournalEntity getbyTitle(String title) {
       return  journalRepository.findByTitle(title);
    }

    @Override
    public JournalEntity getByID(ObjectId id) {
        return journalRepository.findById(id).orElse(null);
    }

    @Override
    public JournalEntity updateJournalBody(ObjectId id, JournalEntity journalEntity) {
        Optional<JournalEntity> optionalJournalEntity = journalRepository.findById(id);
        if(optionalJournalEntity.isPresent())
        {
             JournalEntity journalEntity2=optionalJournalEntity.get();
             // for the content checkk...
             if(journalEntity.getContent() != null) {
                 journalEntity2.setContent(journalEntity.getContent());
             }
            // for the set the Title
             if(journalEntity.getTitle()!=null)
             {
                  journalEntity2.setTitle(journalEntity.getTitle());
             }
             // for the update of the date
             if(journalEntity.getDate()!=null)
             {
                   journalEntity2.setDate(journalEntity.getDate());
             }
             return journalRepository.save(journalEntity2);
        }
        else{
            return journalRepository.save(journalEntity);
        }
    }

    @Override
    public void deleteId(ObjectId id,String userName) {
        Optional<User> user=userRepository.findByUserName(userName);
                if(user.isPresent())
                {
                   User user1=user.get();
                    user1.getJournalEntityList().removeIf(journal -> journal.getJournalId().equals(id));

                    userRepository.save(user1);
                }
                journalRepository.deleteById(id);


    }
}

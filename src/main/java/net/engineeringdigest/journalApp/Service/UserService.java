package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User saveAllUser(User user);
    List<User> getAllUser();
    User getByID(ObjectId id);
    Optional<User> getbyName(String userName);
     void deleteId(ObjectId id);
     User updateUser(String userName,User user);
}

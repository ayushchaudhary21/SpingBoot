package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void saveAllUser(User user);
    void saveAdmin(User user);
    List<User> getAllUser();
    User getByID(ObjectId id);
    Optional<User> getbyName(String userName);
     void deletebyUserName(String  userName);
     User updateUser(String userName,User user);
}

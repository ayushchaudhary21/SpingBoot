package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntity;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Respository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplem implements  UserService{
    
    @Autowired
    private UserRepository userRepository;



    @Override
    public User saveAllUser(User user){
          return userRepository.save(user);
    }
    
    @Override
    public List<User> getAllUser()
    {
         return userRepository.findAll();
    }

    @Override
    public Optional<User> getbyName(String userName) {
       return  userRepository.findByUserName(userName);
    }

    @Override
    public User getByID(ObjectId id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteId(ObjectId id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(String userName, User user) {
          Optional<User> userDb=userRepository.findByUserName(userName);
          if(userDb.isPresent())
          {
              User user1=userDb.get();
              if(user.getPassword()!=null)
              {
                  user1.setPassword(user.getPassword());
              }
              if(user.getJournalEntityList()!=null)
              {
                  user1.setJournalEntityList(user.getJournalEntityList());
              }
              return userRepository.save(user1);
          }
         return null;

    }
}


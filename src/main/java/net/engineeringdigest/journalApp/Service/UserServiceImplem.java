package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntity;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Respository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplem implements  UserService{
    
    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public void saveAllUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User"));
        userRepository.save(user);

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
    public void deletebyUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    @Override
    public User updateUser(String userName, User user) {
          Optional<User> userDb=userRepository.findByUserName(userName);
          if(userDb.isPresent())
          {
              User user1=userDb.get();
              if(user.getPassword()!=null)
              {
                  user1.setPassword(passwordEncoder.encode(user.getPassword()));
              }
              if(user.getJournalEntityList()!=null)
              {
                  user1.setJournalEntityList(user.getJournalEntityList());
              }
              if(user.getUserName()!=null)
              {
                  user1.setUserName(user.getUserName());
              }
              return userRepository.save(user1);
          }
         return null;

    }
}


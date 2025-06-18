package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
     private UserService userService;
    @PostMapping("/userenter")
    public ResponseEntity<?> setAll(@RequestBody User user)
    {
         return new ResponseEntity<>(userService.saveAllUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getAllUser()
    {
         return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }
    @GetMapping("/findbyUsername/{userName}")
    public ResponseEntity<?> findByUserName(@PathVariable ("userName") String userName) {
        Optional<User> user = userService.getbyName(userName);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User did not found");
    }
    @PutMapping("updateUser/{user}")
    public ResponseEntity<?> update(@PathVariable ("user") String userName,
                        @RequestBody User user)
    {
        userService.updateUser(userName,user);
        return ResponseEntity.ok("user updated ");
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable("id") ObjectId id)
    {
        userService.deleteId(id);
        ResponseEntity.status(HttpStatus.ACCEPTED).body("User is deleted");

    }



}

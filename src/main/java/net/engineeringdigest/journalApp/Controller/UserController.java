package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/user")
public class UserController {

    @Autowired
     private UserService userService;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        // SecurityContextHolder save all the context that are done by the spring when the spring
         // done authentication all the username and password detail save in that container.
        String userName=authentication.getName();
        userService.updateUser(userName,user);
        return ResponseEntity.ok("user updated ");
    }


    @DeleteMapping
    public void delete()
    {
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName=authentication.getName();
        userService.deletebyUserName(userName);
        ResponseEntity.status(HttpStatus.ACCEPTED).body("User is deleted");
    }
}








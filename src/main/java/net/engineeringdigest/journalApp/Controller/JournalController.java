package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;

    @PostMapping("save")
    public ResponseEntity<?> saveJournal(@RequestBody JournalEntity journalEntry)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
         return new ResponseEntity<>(journalService.saveAllJournal(journalEntry,userName), HttpStatus.CREATED) ;

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntity>> getALlJournalService()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
          String userName=authentication.getName();
        return new ResponseEntity<>(journalService.getAllJournal(userName),HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getByID(@PathVariable ("id") ObjectId journalId)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        JournalEntity journalEntity=journalService.getByID(journalId,userName);
         if(journalEntity != null)
         {
               return  ResponseEntity.ok(journalEntity);
         }
         else{
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id have no journal");
         }
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity  deleteById(@PathVariable ("id") ObjectId journalId)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        journalService.deleteId(journalId,userName);
         return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("updateId/{id}")
    public ResponseEntity<?> uppdateJournal(@PathVariable ObjectId id,
                                            @RequestBody JournalEntity jorurnalEntry) {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
          return new ResponseEntity<>(journalService.updateJournalBody(id,jorurnalEntry,userName),HttpStatus.ACCEPTED);
    }


}

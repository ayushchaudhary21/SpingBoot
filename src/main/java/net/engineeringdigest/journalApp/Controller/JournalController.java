package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.JournalEntity;
import net.engineeringdigest.journalApp.Service.JournalService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private UserService userService;

    @PostMapping("save/{userName}")
    public ResponseEntity<?> saveJournal(@RequestBody JournalEntity journalEntry,
                                         @PathVariable ("userName") String userName)
    {
         return new ResponseEntity<>(journalService.saveAllJournal(journalEntry,userName), HttpStatus.CREATED) ;

    }
    @GetMapping("/getAll/{username}")
    public ResponseEntity<List<JournalEntity>> getALlJournalService(@PathVariable ("username") String userName )
    {
        return new ResponseEntity<>(journalService.getAllJournal(userName),HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getByID(@PathVariable ("id") ObjectId journalId)
    {
        JournalEntity journalEntity=journalService.getByID(journalId);
         if(journalEntity != null)
         {
               return  ResponseEntity.ok(journalEntity);
         }
         else{
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This id have no journal");
         }
    }
    @GetMapping("getBytitle/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable ("title") String journalTitle){
          JournalEntity journalEntity = journalService.getbyTitle(journalTitle);
          if(journalEntity != null)
          {
               return new ResponseEntity<>(journalEntity,HttpStatus.ACCEPTED);
          }
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @DeleteMapping("/deletebyid/{id}/{username}")
    public ResponseEntity  deleteById(@PathVariable ("id") ObjectId journalId
                                      ,@PathVariable ("username") String userName)
    {
        journalService.deleteId(journalId,userName);
         return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("updateId/{id}")
    public ResponseEntity<?> uppdateJournal(@PathVariable ObjectId id,
                                            @RequestBody JournalEntity jorurnalEntry)
    {
          return new ResponseEntity<>(journalService.updateJournalBody(id,jorurnalEntry),HttpStatus.ACCEPTED);
    }


}

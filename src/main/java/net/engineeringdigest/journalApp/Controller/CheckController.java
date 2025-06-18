package net.engineeringdigest.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @GetMapping("/check")
    public String checking(){
        return "Ok";
    }
}

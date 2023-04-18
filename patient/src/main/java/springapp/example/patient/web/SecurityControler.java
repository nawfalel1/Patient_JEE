package springapp.example.patient.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControler {

    @GetMapping("/403")
    public String notAuthorized(){
        return "403";
    }


}

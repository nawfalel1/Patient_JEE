package springapp.example.patient.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springapp.example.patient.entities.Patient;
import springapp.example.patient.repositories.PatientRepositorie;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientControler {

        private PatientRepositorie patientRepositorie ;

        @GetMapping("/index")
        public String patient(Model model, @RequestParam(name="page",defaultValue = "0") int page,
                              @RequestParam(name = "size",defaultValue = "5") int size,
                              @RequestParam(name = "keyword",defaultValue = "")String keyword){
            Page<Patient> pagepatients = patientRepositorie.findByNomContains(keyword,PageRequest.of(page,size));

            model.addAttribute("listpatient",pagepatients.getContent()) ;
            model.addAttribute("pages",new int[pagepatients.getTotalPages()] ) ;

            model.addAttribute("currentpage",page) ;
            model.addAttribute("keyword",keyword);

            return "patient" ;
        }

        @GetMapping("/test")
        public String test(){
            return "test" ;
        }

        @GetMapping("/supprimer")
        public String supprimer(Long id,String keyword ,int page){
                patientRepositorie.deleteById(id);
                return "redirect:/index?page="+page+"&keyword="+keyword  ;
        }


        @GetMapping("/")
        public String home(){

            return "home" ;
        }

        @GetMapping("/formPatient")
        public String formPatient(Model model){

            model.addAttribute("patient",new Patient());
            return "formPatient" ;
        }


        @GetMapping("/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult){

            if (bindingResult.hasErrors()) return "formPatient" ;
            patientRepositorie.save(patient);
            return "redirect:/index";
        }

        @GetMapping("/editPatient")
        public String editPatient(Model model , Long id ,String keyword ,int page){

                 Patient patient= patientRepositorie.findById(id).orElse(null);
                 if(patient==null) throw new RuntimeException("Patient Introuvable") ;

                 model.addAttribute("patient",patient) ;
                 model.addAttribute("keyword",keyword);
            model.addAttribute("page",page) ;
                return "editPatient" ;

        }


}

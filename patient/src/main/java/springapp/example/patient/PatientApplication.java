package springapp.example.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springapp.example.patient.entities.Patient;
import springapp.example.patient.repositories.PatientRepositorie;

import java.util.Date;

@SpringBootApplication
public class PatientApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientApplication.class, args);
	}


	//@Bean
	CommandLineRunner commandeLineRunner(PatientRepositorie patientRepositorie){
		return args -> {
			 	patientRepositorie.save(new Patient(null,"hamza",new Date(),false,12));
			patientRepositorie.save(new Patient(null,"ayman",new Date(),true,48));
			patientRepositorie.save(new Patient(null,"zaid",new Date(),false,37));
			patientRepositorie.findAll().forEach(p-> System.out.println(p.getNom())
			);
		};
	}

}

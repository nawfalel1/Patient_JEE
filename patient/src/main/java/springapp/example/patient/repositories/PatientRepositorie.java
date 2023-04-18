package springapp.example.patient.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springapp.example.patient.entities.Patient;

public interface PatientRepositorie extends JpaRepository<Patient,Long> {

    Page<Patient> findByNomContains(String kw , Pageable pageable) ;
}

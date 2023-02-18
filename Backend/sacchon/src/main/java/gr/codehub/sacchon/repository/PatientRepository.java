package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdIgnoreCase(String patientEmailId);

    void deleteByPatientEmailId(String patientEmailId);
}

package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdIgnoreCase(String patientEmailId);

    @Query(value ="select p from Patient p  where p.patientsDoctor=:doctor or p.patientsDoctor is null" )
    List<Patient> findPatientsByPatientsDoctorAndNotHavingADoctor(Doctor doctor);



}

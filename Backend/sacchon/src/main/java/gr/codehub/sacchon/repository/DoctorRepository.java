package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByDoctorEmailIdIgnoreCase(String doctorEmailId);



}

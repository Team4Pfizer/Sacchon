package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.ChiefDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiefDoctorRepository extends JpaRepository<ChiefDoctor,Long> {
}

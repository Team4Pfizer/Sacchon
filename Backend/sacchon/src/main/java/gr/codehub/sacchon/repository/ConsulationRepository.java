package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsulationRepository extends JpaRepository<Consultation,Long> {

    @Query(value = "select c from Consultation c  where c.patient=:patient")
    List<Consultation> findConsultationByPatient(Patient patient);

}

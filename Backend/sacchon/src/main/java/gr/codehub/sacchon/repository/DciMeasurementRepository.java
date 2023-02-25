package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DciMeasurementRepository extends JpaRepository<DciMeasurement,Long> {

    @Query(value = "select dci from DciMeasurement dci  where dci.dciMeasurementDate>=:start and dci.dciMeasurementDate<=:stop and dci.patient=:patient")
    List<DciMeasurement> findDciMeasurementByDciMeasurementDateIsBetweenAndPatient(LocalDate start, LocalDate stop, Patient patient);


    @Query(value = "select dci from DciMeasurement dci  where dci.dciMeasurementId=:measurementId and dci.patient=:patient")
    Optional<DciMeasurement> findDciMeasurementByIdAndPatient(Long measurementId, Patient patient);

    @Query(value = "select dci from DciMeasurement dci  where dci.patient=:patient")
    List<DciMeasurement> findDciMeasurementByPatient(Patient patient);

    @Query(value = "select dci from DciMeasurement dci  where dci.patient=:patient and dci.dciMeasurementDate>=:localDate")
    List<DciMeasurement> findDciMeasurementByPatientAndAfterDate(Patient patient,LocalDate localDate);


}

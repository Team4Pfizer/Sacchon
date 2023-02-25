package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BgMeasurementRepository extends JpaRepository<BgMeasurement,Long> {

    @Query(value = "select bgm from BgMeasurement bgm  where bgm.BgMeasurementDate>=:start and bgm.BgMeasurementDate<=:stop and bgm.patient=:patient")
    List<BgMeasurement> findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(LocalDate start,LocalDate stop,Patient patient);


    @Query(value = "select bgm from BgMeasurement bgm  where bgm.BgMeasurementId=:measurementId and bgm.patient=:patient")
    Optional<BgMeasurement> findBgMeasurementByIdAndPatient(Long measurementId, Patient patient);

    @Query(value = "select bgm from BgMeasurement bgm  where bgm.patient=:patient")
    List<BgMeasurement> findBgMeasurementByPatient(Patient patient);

    @Query(value = "select bgm from BgMeasurement bgm  where bgm.patient=:patient and bgm.BgMeasurementDate>=:localDate")
    List<BgMeasurement> findBgMeasurementByPatientAndAfterDate(Patient patient,LocalDate localDate);





}

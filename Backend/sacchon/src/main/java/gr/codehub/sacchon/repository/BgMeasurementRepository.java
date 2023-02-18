package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface BgMeasurementRepository extends JpaRepository<BgMeasurement,Long> {

    @Query(value = "select bgm from BgMeasurement bgm  where bgm.BgMeasurementDate>=:start and bgm.BgMeasurementDate<=:stop and bgm.patient=:patient")
    List<BgMeasurement> findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(LocalDate start,LocalDate stop,Patient patient);


    @Query(value = "select bgm from BgMeasurement bgm  where bgm.BgMeasurementDate=:measurementDate and bgm.BgMeasurementTime=:measurementTime and bgm.patient=:patient")
    Optional<BgMeasurement> findBgMeasurementByDateByTimeAndPatient(LocalDate measurementDate, LocalTime measurementTime, Patient patient);
}

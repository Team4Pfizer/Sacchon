package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    @Query(value = "select c from Consultation c  where c.patient=:patient")
    List<Consultation> findConsultationByPatient(Patient patient);

    @Query(value = "select count (c) from Consultation c  where c.patient.patientsDoctor=:doctor")
    int findSumOfConsultationsByDoctor(Doctor doctor);
    @Query(value = "select count (c) from Consultation c  where c.patient.patientsDoctor=:doctor and " +
            "c.consultationDate>=:date")
    int findSumOfLastMonthConsultationsByDoctorAndDate(Doctor doctor, LocalDate date);


    @Query(value = "select c from Consultation c  where c.patient.patientsDoctor=:doctor and " +
            "c.consultationDate>=:start and c.consultationDate<=:stop")
    List<Consultation> findSumOfConsultationsByDoctorAndBetweenDates(Doctor doctor, LocalDate start,LocalDate stop);



}

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


    @Query(value = "SELECT p.patient_id,p.patient_email_id,count(c.patient_id) \n" +
                  " FROM consultations c , patients p where c.consultation_date>=?1 and \n" +
                  " c.consultation_date<=?2 and p.patient_id=c.patient_id group by p.patient_email_id,p.patient_id",nativeQuery = true)
    List<Object[]> findPatientAndNoOfConsultationsOverTimeRange(LocalDate start, LocalDate stop);


}

package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {


    @Query(value = "select d.doctor_email_id,d.doctor_first_name,d.doctor_last_name from doctors d where " +
            "(select count(con.patient_id) from consultations con,patients p where con.consultation_date>=?1 " +
            "and con.consultation_date<=?2 and con.patient_id=p.patient_id and p.doctor_id=d.doctor_id)=0",nativeQuery = true)
    List<Object[]> findDoctorsWithNoActivityOverTimePeriod(LocalDate start,LocalDate stop);



}

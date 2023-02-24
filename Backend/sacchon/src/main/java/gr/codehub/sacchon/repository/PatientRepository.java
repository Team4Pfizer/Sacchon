package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdIgnoreCase(String patientEmailId);

    @Query(value = "select * from patients pt where " +
            "DATEADD(month,1,(SELECT min(dci_measurement_date) FROM dci_measurements dci1 where dci1.patient_id=pt.patient_id))" +
            "<= (SELECT Max(dci_measurement_date) FROM dci_measurements dci2 where dci2.patient_id=pt.patient_id) and" +
            " DATEADD(month,1,(SELECT min(bg_measurement_date) FROM bg_measurements bg1 where bg1.patient_id=pt.patient_id))<=" +
            " (SELECT Max(bg_measurement_date) FROM bg_measurements bg2 where bg2.patient_id=pt.patient_id) " +
            "and (pt.doctor_id IS NULL or pt.doctor_id=?1)" ,nativeQuery = true)
    List<Patient> findPatientsHavingMoreThanMonthOfMeasurementsAndTheyAreAvailable(Long doctorId);


    @Query(value = "select * from patients pt where " +
            "(DATEADD(month,1,(SELECT min(dci_measurement_date) FROM dci_measurements dci1 where dci1.patient_id=pt.patient_id))" +
            "<= (SELECT Max(dci_measurement_date) FROM dci_measurements dci2 where dci2.patient_id=pt.patient_id) and" +
            " DATEADD(month,1,(SELECT min(bg_measurement_date) FROM bg_measurements bg1 where bg1.patient_id=pt.patient_id))" +
            "<=(SELECT Max(bg_measurement_date) FROM bg_measurements bg2 where bg2.patient_id=pt.patient_id) " +
            "and pt.doctor_id IS NULL) or ((select (DATEADD(month,1,(SELECT max(con.consultation_date) from consultations con " +
            "where con.patient_id=pt.patient_id))))<=?1 AND pt.doctor_id is not null)" ,nativeQuery = true)
    List<Patient> findPatientsWaitingConsultations (LocalDate NowDate);

//TODO : The query


//    @Query(value = "select patient_id from patients pt where (select COUNT(*) from bg_measurements where bg_measurement_date>='2022-08-01' and bg_measurement_date<='2022-08-01' and bg_measurements.patient_id=pt.patient_id)=0")
    @Query(value = "select p.patient_id,p.patient_email_id,p.patient_first_name,p.patient_last_name from patients p where \n" +
            "(select count(*) from bg_measurements \n" +
            "where bg_measurements.bg_measurement_date>=?1 and\n" +
            "bg_measurements.bg_measurement_date<=?2 and bg_measurements.patient_id=p.patient_id)=0", nativeQuery = true)
    List<Object[]> findPatientsWithNoActivityOverTimePeriod(LocalDate start,LocalDate stop);

}

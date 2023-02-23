package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
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
            " DATEADD(month,1,(SELECT min(bg_measurement_date) FROM bg_measurements bg1 where bg1.patient_id=pt.patient_id))<=" +
            " (SELECT Max(bg_measurement_date) FROM bg_measurements bg2 where bg2.patient_id=pt.patient_id) " +
            "and pt.doctor_id IS NULL) or  " ,nativeQuery = true)
    List<Patient> findPatientsWaitingConsulations (LocalDate NowDate);

//TODO : The query
//




}

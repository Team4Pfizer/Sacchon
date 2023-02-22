package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdIgnoreCase(String patientEmailId);

    @Query(value =
            "select * from patients pt where DATEADD(month,1,(SELECT min(dci_measurement_date) FROM dci_measurements dci1" +
                    " where dci1.patient_id=pt.patient_id))<=(SELECT Max(dci_measurement_date) FROM dci_measurements dci2" +
                    " where dci2.patient_id=pt.patient_id) and (pt.doctor_id IS NULL )",nativeQuery = true)
//    OR pt.doctor_id=1?
    List<Patient> findPatientsHavingMoreThanMonthOfMeasurementsAndTheyAreAvailable();







}

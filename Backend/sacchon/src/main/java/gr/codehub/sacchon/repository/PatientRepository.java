package gr.codehub.sacchon.repository;

import gr.codehub.sacchon.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientEmailIdIgnoreCase(String patientEmailId);

    @Query(value =
            "select * from patients where patient_id in (select distinct(dci.patient_id) from dci_measurements dci where " +
                    "datediff(day,(select min(dci_measurement_date) from dci_measurements dci1 where dci1.patient_id=dci.patient_id)," +
                    "(select max(dci_measurement_date) from dci_measurements dci2 where dci2.patient_id=dci.patient_id)) >3)",nativeQuery = true)
    List<Patient> findPatientsHavingMoreThanMonthOfMeasurements();




}

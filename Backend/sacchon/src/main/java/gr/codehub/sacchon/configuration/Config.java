package gr.codehub.sacchon.configuration;

import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.*;
import gr.codehub.sacchon.service.MediDataVaultService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Configuration
public class Config {

    @Bean
    public Clock clock() {

        return Clock.systemDefaultZone();
//        return Clock.fixed(Instant.parse("2022-08-01T10:15:00.00Z"),
//                Clock.systemDefaultZone().getZone());
    }


//    @Bean
//    CommandLineRunner commandLineRunner(
//            DoctorRepository doctorRepository,
//            PatientRepository patientRepository,
//            ConsultationRepository consultationRepository,
//            BgMeasurementRepository bgMeasurementRepository,
//            DciMeasurementRepository dciMeasurementRepository,
//            Clock clock
//
//
//            ){
//        return args -> {
////            Patient patient1 = new Patient(0L,"michael.lawson@gmail.com","Michael","lawson",false,null);
////            Patient patient2 = new Patient(0L,"lindsay.ferguson@gmail.com","Lindsay","Ferguson",false,null);
////
////            Doctor doctor1 = new Doctor(0L,"byron.fields@gmail.com","Byron","Fields",null);
////            Doctor doctor2 = new Doctor(0L,"tobias.funke@gmail.com","Tobias","Funke",null);
////            patientRepository.save(patient1);
////            patientRepository.save(patient2);
////            doctorRepository.save(doctor1);
////            doctorRepository.save(doctor2);
//
//            Optional<Patient> patient = patientRepository.findById(2L);
//
//
//            for (int i=0 ; i<35;i++){
//                Clock movableClock = Clock.offset(clock, Duration.ofDays(i+1));
//                BgMeasurement bgMeasurement = new BgMeasurement(
//                        0L,
//                        120.00,
//                        LocalDate.now(movableClock),
//                        LocalTime.now(movableClock),patient.get());
//                DciMeasurement dciMeasurement = new DciMeasurement(
//                        0L,
//                        250.5,
//                        LocalDate.now(movableClock),
//                        patient.get());
//                bgMeasurementRepository.save(bgMeasurement);
//                dciMeasurementRepository.save(dciMeasurement);
//
//            }
//
//
//        };
//    }
}






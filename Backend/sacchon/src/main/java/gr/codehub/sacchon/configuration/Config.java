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
import java.util.Optional;

@Configuration
public class Config {

    @Bean
    public Clock clock() {

        return Clock.systemDefaultZone();

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
//            Optional<Patient> patient = patientRepository.findById(1L);
//
//            for (int i=0 ; i<35;i++){
//                Clock movableClock = Clock.offset(clock, Duration.ofDays(i));
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

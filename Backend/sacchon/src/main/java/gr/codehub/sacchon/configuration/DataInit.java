package gr.codehub.sacchon.configuration;

import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
public class DataInit {
    @Bean
    CommandLineRunner commandLineRunner(
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            ConsultationRepository consultationRepository,
            BgMeasurementRepository bgMeasurementRepository,
            DciMeasurementRepository dciMeasurementRepository,
            ChiefDoctorRepository chiefDoctorRepository,
            PasswordEncoder passwordEncoder,
            Clock clock


    ) {
        return args -> {
            ChiefDoctor chiefDoctor = new ChiefDoctor(0L,"test.chief@test.com","TestName","TestLastName",passwordEncoder.encode("password"));
            chiefDoctorRepository.save(chiefDoctor);
            Doctor doctor1 = new Doctor(0L,"byron.fields@gmail.com","Byron","Fields",null);
            Doctor doctor2 = new Doctor(0L,"tobias.funke@gmail.com","Tobias","Funke",null);
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);

            Optional<Doctor> doctor = doctorRepository.findById(1L);

            Patient patient1 = new Patient(0L,"michael.lawson@gmail.com","Michael","lawson",false,doctor.get());
            Patient patient2 = new Patient(0L,"lindsay.ferguson@gmail.com","Lindsay","Ferguson",false,null);


            patientRepository.save(patient1);
            patientRepository.save(patient2);


            Optional<Patient> patientOptional1 = patientRepository.findById(1L);
            Optional<Patient> patientOptional2 = patientRepository.findById(2L);




            for (int i = 0; i < 35; i++) {
                Clock movableClock = Clock.offset(clock, Duration.ofDays(-36+i));
                BgMeasurement bgMeasurement1 = new BgMeasurement(
                        0L,
                        120.00,
                        LocalDate.now(movableClock),
                        LocalTime.now(movableClock), patientOptional1.get());

                DciMeasurement dciMeasurement1 = new DciMeasurement(
                        0L,
                        250.5,
                        LocalDate.now(movableClock),
                        patientOptional1.get());
                bgMeasurementRepository.save(bgMeasurement1);
                dciMeasurementRepository.save(dciMeasurement1);
                BgMeasurement bgMeasurement2 = new BgMeasurement(
                        0L,
                        120.00,
                        LocalDate.now(movableClock),
                        LocalTime.now(movableClock), patientOptional2.get());
                DciMeasurement dciMeasurement2 = new DciMeasurement(
                        0L,
                        250.5,
                        LocalDate.now(movableClock),
                        patientOptional2.get());

                bgMeasurementRepository.save(bgMeasurement2);
                dciMeasurementRepository.save(dciMeasurement2);

            }
            Clock movableClock = Clock.offset(clock, Duration.ofDays(-1));
            Consultation consultation = new Consultation(0L,1.00,"Insulin",LocalDate.now(movableClock),patientOptional1.get());

            consultationRepository.save(consultation);






        };
    }

}

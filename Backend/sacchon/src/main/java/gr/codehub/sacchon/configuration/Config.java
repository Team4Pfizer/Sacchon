package gr.codehub.sacchon.configuration;

import gr.codehub.sacchon.model.*;
import gr.codehub.sacchon.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Configuration
public class Config {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET","POST",  "PUT", "DELETE");
            }
        };
    }

    @Bean
    public Clock clock() {

        return Clock.systemDefaultZone();
    }


    @Bean
    CommandLineRunner commandLineRunner(
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            ConsultationRepository consultationRepository,
            BgMeasurementRepository bgMeasurementRepository,
            DciMeasurementRepository dciMeasurementRepository,
            Clock clock


            ) {
        return args -> {
            Doctor doctor1 = new Doctor(0L,"byron.fields@gmail.com","Byron","Fields",null);
            Doctor doctor2 = new Doctor(0L,"tobias.funke@gmail.com","Tobias","Funke",null);
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);

            Optional<Doctor> doctor = doctorRepository.findById(1L);

            Patient patient1 = new Patient(0L,"michael.lawson@gmail.com","Michael","lawson",false,doctor.get());
            Patient patient2 = new Patient(0L,"lindsay.ferguson@gmail.com","Lindsay","Ferguson",false,null);


            patientRepository.save(patient1);
            patientRepository.save(patient2);

            Optional<Patient> patient = patientRepository.findById(1L);




            for (int i = 0; i < 35; i++) {
                Clock movableClock = Clock.offset(clock, Duration.ofDays(37-i));
                BgMeasurement bgMeasurement = new BgMeasurement(
                        0L,
                        120.00,
                        LocalDate.now(movableClock),
                        LocalTime.now(movableClock), patient.get());
                DciMeasurement dciMeasurement = new DciMeasurement(
                        0L,
                        250.5,
                        LocalDate.now(movableClock),
                        patient.get());
                bgMeasurementRepository.save(bgMeasurement);
                dciMeasurementRepository.save(dciMeasurement);

            }
            Clock movableClock = Clock.offset(clock, Duration.ofDays(-1));
            Consultation consultation = new Consultation(0L,1.5,"Insulin",LocalDate.now(movableClock),patient.get());

            consultationRepository.save(consultation);






        };
    }
}





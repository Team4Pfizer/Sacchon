package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="DOCTORS")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId ;
    private String userEmail ;
//    private String userPassword;
    private String firstName;
    private String lastName;


    @OneToMany(mappedBy = "doctor")

    private Set<Patient> patients;



//    private Set<Consultation> consultations;



}

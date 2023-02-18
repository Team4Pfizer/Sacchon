package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true)
    private String doctorEmailId ;

    private String doctorFirstName;
    private String doctorLastName;


    @OneToMany(mappedBy = "doctor")

    private Set<Patient> patients;






}

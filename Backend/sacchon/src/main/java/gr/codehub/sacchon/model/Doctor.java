package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
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


    @OneToMany(mappedBy = "patientsDoctor")
    private Set<Patient> patients;






}

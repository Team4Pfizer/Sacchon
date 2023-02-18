package gr.codehub.sacchon.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId ;
    @Column(unique = true)
    private String patientEmailId ;
    private String patientFirstName ;
    private String patientLastName ;



    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctorId")
    private Doctor patientsDoctor;





}

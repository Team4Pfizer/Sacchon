package gr.codehub.sacchon.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId ;

    private String firstName ;
    private String lastName ;


    private String userEmail ;
//    private String userPassword ;
//    // has to be hashed in the db
//
//    private LocalDate registrationDate;
//
//    private int userAge ;
//
//    private boolean hasConsultant; // if the patient has or has no consultant

    //---

    @ManyToOne
    @JoinColumn(
            name = "doctor_Id",
            referencedColumnName = "doctorId"

    )// many patients can have one doctor

    private Doctor doctor; // the patient's doctor


}

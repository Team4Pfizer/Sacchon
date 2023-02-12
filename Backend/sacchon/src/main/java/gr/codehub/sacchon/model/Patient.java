package gr.codehub.sacchon.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private String userPassword ;
    // has to be hashed in the db

    private Date registrationDate;

    private int userAge ;

    //---




}

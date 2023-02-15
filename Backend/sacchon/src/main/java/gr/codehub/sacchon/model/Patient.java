package gr.codehub.sacchon.model;


import com.sun.source.doctree.DocTree;
import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
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

    private boolean hasConsultant; // if the patient has or has no consultant

    //---

    @ManyToOne // many patients can have one doctor
    @JoinColumn(name = "doctorId")
    private Doctor doctor; // the patient's doctor

    @OneToMany(mappedBy = "patient")
    private Set<Consultation> consultations = new HashSet<>(); // the patient's consultations

    @OneToMany(mappedBy = "patient")
    private Set<BgMeasurement> bgMeasurements = new HashSet<>(); // the patient's bg measurements

    @OneToMany
    private Set<DciMeasurement> dciMeasurements = new HashSet<>(); // the patient's dci measurements







}

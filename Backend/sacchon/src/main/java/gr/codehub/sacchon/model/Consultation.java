package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private String message ; // the consultation message
    private Date consultationOfMonth; // not sure if valid



    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")

    private Patient patient; // the patient that the consultation is abou

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // the doctor that the consultation is abou




}

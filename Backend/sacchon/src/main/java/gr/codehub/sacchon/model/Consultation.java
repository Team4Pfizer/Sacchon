package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="CONSULTATIONS")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private Double consultationDosage;

    private String consultationMedication;
    private LocalDate consultationDate;



    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "patientId")

    private Patient patient;


}

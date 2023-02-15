package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DciMeasurement {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long measurementId;

    private double dciMeasurementData;
    private LocalDate dciMeasurementDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}

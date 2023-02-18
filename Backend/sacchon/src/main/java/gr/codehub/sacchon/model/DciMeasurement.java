package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "DCI_MEASUREMENTS")
public class DciMeasurement {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long dciMeasurementId;

    private int dciMeasurementData;
    private LocalDate dciMeasurementDate;

    @ManyToOne
    @JoinColumn(name="patient_Id", referencedColumnName = "patientId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;


}

package gr.codehub.sacchon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

}

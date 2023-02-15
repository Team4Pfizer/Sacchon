package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DciMeasurement that = (DciMeasurement) o;

        return Objects.equals(measurementId, that.measurementId);
    }

    @Override
    public int hashCode() {
        return measurementId != null ? measurementId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DciMeasurement{" +
                "measurementId=" + measurementId +
                ", dciMeasurementData=" + dciMeasurementData +
                ", dciMeasurementDate=" + dciMeasurementDate +
                ", patient=" + patient +
                '}';
    }
}

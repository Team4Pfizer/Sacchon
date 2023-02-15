package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BG_MEASUREMENTS")
public class BgMeasurement {


    //blood glucose level (date, time, measured in mg/dL)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId ;

    private double measurementMg;
    private LocalDate measurementDate;

    private LocalTime measurementTime;

    @ManyToOne (fetch = FetchType.LAZY)
       @JoinTable(name = "PATIENT_BG_MEASUREMENTS",
            joinColumns = @JoinColumn(name = "measurement_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BgMeasurement that = (BgMeasurement) o;

        return Objects.equals(measurementId, that.measurementId);
    }

    @Override
    public int hashCode() {
        return measurementId != null ? measurementId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BgMeasurement{" +
                "measurementId=" + measurementId +
                ", measurementMg=" + measurementMg +
                ", measurementDate=" + measurementDate +
                ", measurementTime=" + measurementTime +
                ", patient=" + patient +
                '}';
    }
}

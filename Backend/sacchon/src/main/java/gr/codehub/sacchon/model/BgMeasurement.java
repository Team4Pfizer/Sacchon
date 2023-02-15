package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class BgMeasurement {


    //blood glucose level (date, time, measured in mg/dL)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId ;

    private double measurementMg;
    private Date measurementDate;

    private Time measurementTime;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id") // needs fixing
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

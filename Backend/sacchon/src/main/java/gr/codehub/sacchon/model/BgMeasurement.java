package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BG_MEASUREMENTS")
public class BgMeasurement {


    //blood glucose level (date, time, measured in mg/dL)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BgMeasurementId ;

    private int BgMeasurementData;
    private LocalDate BgMeasurementDate;

    private LocalTime BgMeasurementTime;

    @ManyToOne
    @JoinColumn(name="patient_Id", referencedColumnName = "patientId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;


}

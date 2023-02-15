package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;


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
    @JoinColumn(name = "patient_id")
    private Patient patient;




}

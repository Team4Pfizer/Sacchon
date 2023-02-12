package gr.codehub.sacchon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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




}

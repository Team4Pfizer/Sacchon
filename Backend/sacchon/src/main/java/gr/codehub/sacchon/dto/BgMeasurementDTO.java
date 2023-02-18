package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Patient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BgMeasurementDTO {

    private int bgMeasurementData;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgMeasurementDate;

    @DateTimeFormat(pattern = "HHmm")
    private LocalTime bgMeasurementTime;

    public BgMeasurementDTO(BgMeasurement bgMeasurement){
        this.bgMeasurementData=bgMeasurement.getBgMeasurementData();
        this.bgMeasurementDate=bgMeasurement.getBgMeasurementDate();
        this.bgMeasurementTime=bgMeasurement.getBgMeasurementTime();
    }

    public BgMeasurement toEntity(Patient patient){
        return BgMeasurement.builder()
                .BgMeasurementData(this.bgMeasurementData)
                .BgMeasurementDate(this.bgMeasurementDate)
                .BgMeasurementTime(this.bgMeasurementTime)
                .patient(patient)
                .build();
    }


}

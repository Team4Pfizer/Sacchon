package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Patient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BgMeasurementDTO {
    private Long bgMeasurementId;

    private int bgMeasurementData;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgMeasurementDate;

    @JsonFormat(pattern = "HHmm")
    private LocalTime bgMeasurementTime;

    public BgMeasurementDTO(BgMeasurement bgMeasurement){
        this.bgMeasurementId=bgMeasurement.getBgMeasurementId();
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

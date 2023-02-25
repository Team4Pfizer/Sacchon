package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.validate.CustomDateDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BgMeasurementDTO {


    private Long bgMeasurementId;

    @Positive(message="bgMeasurementData Data is >0")
    @NotNull(message="bgMeasurementData Data is required field")
    private Double bgMeasurementData;

    @JsonDeserialize(using = CustomDateDeserializer.class)
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

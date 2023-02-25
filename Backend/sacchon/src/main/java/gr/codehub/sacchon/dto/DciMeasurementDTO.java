package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.validate.CustomDateDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DciMeasurementDTO {


    private Long dciMeasurementId;


    @Positive(message="dciMeasurementData Data is >0")
    @NotNull(message="dciMeasurementData Data is required field")
    private Double dciMeasurementData;


    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate dciMeasurementDate;

    public DciMeasurementDTO(DciMeasurement dciMeasurement){
        this.dciMeasurementId=dciMeasurement.getDciMeasurementId();
        this.dciMeasurementData= dciMeasurement.getDciMeasurementData();
        this.dciMeasurementDate=dciMeasurement.getDciMeasurementDate();
    }

    public DciMeasurement toEntity(Patient patient){
        return DciMeasurement
                .builder()
                .dciMeasurementData(this.dciMeasurementData)
                .dciMeasurementDate(this.dciMeasurementDate)
                .patient(patient)
                .build();
    }


}

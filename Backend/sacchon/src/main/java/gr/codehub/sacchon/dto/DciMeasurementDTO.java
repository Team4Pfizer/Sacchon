package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import lombok.*;

import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DciMeasurementDTO {


    private Long dciMeasurementId;

    private Double dciMeasurementData;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

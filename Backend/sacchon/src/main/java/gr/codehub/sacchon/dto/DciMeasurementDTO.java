package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DciMeasurementDTO {

    private int dciMeasurementData;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dciMeasurementDate;

    public DciMeasurementDTO(DciMeasurement dciMeasurement){
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

package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DciMeasurementDTO {

    private int dciMeasurementData;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dciMeasurementDate;
}

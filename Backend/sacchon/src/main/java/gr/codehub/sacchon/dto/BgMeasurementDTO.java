package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BgMeasurementDTO {

    private int bgMeasurementData;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bgMeasurementDate;

    @JsonFormat(pattern = "HHmm")
    private LocalTime bgMeasurementTime;


}

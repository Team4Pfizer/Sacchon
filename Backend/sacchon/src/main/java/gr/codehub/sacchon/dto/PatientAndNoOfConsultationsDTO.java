package gr.codehub.sacchon.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientAndNoOfConsultationsDTO {
    private Long patientId;
    private String patientEmailId;

    private Integer totalConsultations;
}

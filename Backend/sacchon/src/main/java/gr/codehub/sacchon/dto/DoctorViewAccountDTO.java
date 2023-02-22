package gr.codehub.sacchon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorViewAccountDTO {
    private DoctorDTO doctor;

    private int totalConsultations;

    private int lastMonthConsultations;




}

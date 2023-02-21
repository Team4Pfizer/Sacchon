package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorViewAccountDTO {
    private DoctorDTO doctor;

    private int totalConsultations;

    private int lastMonthConsultations;

    private List<Patient> doctorsPatientList;



}

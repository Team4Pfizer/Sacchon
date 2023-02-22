package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientForDoctorViewDTO {
    private Long patientId ;

    private String patientFirstName ;
    private String patientLastName ;
    private String patientEmailId ;




    public PatientForDoctorViewDTO(Patient patient){
        this.patientId=patient.getPatientId();
        this.patientEmailId=patient.getPatientEmailId();
        this.patientFirstName=patient.getPatientFirstName();
        this.patientLastName=patient.getPatientLastName();

    }
}

package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Patient;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private String patientFirstName ;
    private String patientLastName ;
    private String patientEmailId ;



    public PatientDTO(Patient patient){
        this.patientEmailId=patient.getPatientEmailId();
        this.patientFirstName=patient.getPatientFirstName();
        this.patientLastName=patient.getPatientLastName();

    }


    public Patient toEntity(){
        return Patient.builder()
                .patientEmailId(this.patientEmailId)
                .patientFirstName(this.patientFirstName)
                .patientLastName(this.patientLastName)
                .build();

    }



}

package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long patientId ;

    @NotNull
    @NotEmpty
    private String patientFirstName ;
    @NotNull
    @NotEmpty
    private String patientLastName ;
    @Email
    @NotNull
    @NotEmpty
    private String patientEmailId ;





    public PatientDTO(Patient patient){
        this.patientId=patient.getPatientId();
        this.patientEmailId=patient.getPatientEmailId();
        this.patientFirstName=patient.getPatientFirstName();
        this.patientLastName=patient.getPatientLastName();

    }


    public Patient toEntity(){
        return Patient.builder()
                .patientEmailId(this.patientEmailId)
                .patientFirstName(this.patientFirstName)
                .patientLastName(this.patientLastName)
                .alarm(false)
                .build();

    }



}

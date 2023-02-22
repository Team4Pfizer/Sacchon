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
public class PatientForDoctorViewDTO {
    private Long patientId ;

    private String patientFirstName ;
    private String patientLastName ;
    private String patientEmailId ;


    private List<ConsultationDTO> consultationList;

    private List<BgMeasurementDTO> bgMeasurementList;
    private List<DciMeasurementDTO> dciMeasurementList;




    public PatientForDoctorViewDTO(Patient patient,
                                   List<ConsultationDTO> consultationList,
                                   List<BgMeasurementDTO> bgMeasurementList,
                                   List<DciMeasurementDTO> dciMeasurementList){

        this.patientId=patient.getPatientId();
        this.patientEmailId=patient.getPatientEmailId();
        this.patientFirstName=patient.getPatientFirstName();
        this.patientLastName=patient.getPatientLastName();
        this.consultationList=consultationList;
        this.bgMeasurementList=bgMeasurementList;
        this.dciMeasurementList=dciMeasurementList;

    }
}

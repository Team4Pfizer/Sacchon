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
public class PatientViewAccountDTO {
    private PatientDTO patientDTO;

    private DoctorDTO patientsDoctorDTO;
    private List<BgMeasurementDTO> bgMeasurementDTOList;

    private List<DciMeasurementDTO> dciMeasurementDTOList;

    private List<ConsultationDTO> consultationDTOList;

    public PatientViewAccountDTO(Patient patient,List<BgMeasurementDTO> bgMeasurementDTOList,List<DciMeasurementDTO> dciMeasurementDTOList,List<ConsultationDTO> consultationDTOList){
        this.patientDTO=new PatientDTO(patient);
        try {
            this.patientsDoctorDTO=new DoctorDTO(patient.getPatientsDoctor());
        }catch (NullPointerException e){
            this.patientsDoctorDTO=null;
        }

        this.bgMeasurementDTOList=bgMeasurementDTOList;
        this.dciMeasurementDTOList=dciMeasurementDTOList;
        this.consultationDTOList=consultationDTOList;

    }


}

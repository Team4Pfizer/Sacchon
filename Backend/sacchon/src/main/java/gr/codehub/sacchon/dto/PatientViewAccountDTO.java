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
    private PatientDTO patient;

    private DoctorDTO patientsDoctor;
    private List<BgMeasurementDTO> bgMeasurementList;
    private List<DciMeasurementDTO> dciMeasurementList;




    public PatientViewAccountDTO(Patient patient,List<BgMeasurementDTO> bgMeasurementList,List<DciMeasurementDTO> dciMeasurementList){
        this.patient=new PatientDTO(patient);
        try {
            this.patientsDoctor=new DoctorDTO(patient.getPatientsDoctor());
        }catch (NullPointerException e){
            this.patientsDoctor=null;

        }
        this.bgMeasurementList=bgMeasurementList;
        this.dciMeasurementList=dciMeasurementList;



    }


}

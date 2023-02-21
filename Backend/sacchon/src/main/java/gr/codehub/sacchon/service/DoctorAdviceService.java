package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.dto.PatientViewAccountDTO;
import gr.codehub.sacchon.exception.NotFoundException;

import java.util.List;
import java.util.Map;

public interface DoctorAdviceService {
    Map<String,Object> viewAccount (String doctorEmailId) throws NotFoundException;

    DoctorDTO signUp(DoctorDTO doctorDTO);

    void removeAccount(String doctorEmailId) throws NotFoundException ;

    List<PatientViewAccountDTO> availablePatients(String doctorEmailId) throws NotFoundException;

    ConsultationDTO consultPatient(ConsultationDTO consultationDTO,String emailId, Long patientId)throws NotFoundException;

}

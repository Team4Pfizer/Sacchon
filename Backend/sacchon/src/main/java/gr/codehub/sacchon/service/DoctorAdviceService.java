package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;

import java.util.List;

public interface DoctorAdviceService {
    DoctorViewAccountDTO viewAccount (String doctorEmailId) throws NotFoundException;

    DoctorDTO signUp(DoctorDTO doctorDTO);

    void removeAccount(String doctorEmailId) throws NotFoundException ;

    List<PatientDTO> availablePatients(String doctorEmailId) throws NotFoundException;

    ConsultationDTO consultPatient(ConsultationDTO consultationDTO,String emailId, Long patientId)throws NotFoundException;

    PatientForDoctorViewDTO patientProfile(String doctorEmailId,Long patientId)throws NotFoundException;

}

package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;

import java.util.List;

public interface DoctorAdviceService {
    DoctorViewAccountDTO viewAccount (Long doctorId) throws NotFoundException;

    DoctorDTO signUp(DoctorDTO doctorDTO);

    void removeAccount(Long doctorId) throws NotFoundException ;

    List<PatientDTO> availablePatients(Long doctorId) throws NotFoundException;

    ConsultationDTO consultPatient(ConsultationDTO consultationDTO,Long doctorId, Long patientId)throws NotFoundException;

    PatientForDoctorViewDTO patientProfile(Long doctorId,Long patientId)throws NotFoundException, BadRequestException;

    ConsultationDTO updateConsultation(Long doctorId,Long patientId,ConsultationDTO consultationDTO)throws NotFoundException,BadRequestException;

}

package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ReporterService {
    PatientViewAccountDTO getPatientDataOverTimeRange (Long patientId, LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException;

    List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (Long patientId, LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException;

    List<PatientDTO> getPatientsWhoWaitConsultations (ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException,BadRequestException;

    List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException;

    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException;


    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException;


}

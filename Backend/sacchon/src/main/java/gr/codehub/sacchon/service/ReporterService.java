package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ReporterService {
    PatientViewAccountDTO getPatientDataOverTimeRange (Long patientId, LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException;

    List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (Long patientId, LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException;

    List<PatientDTO> getPatientsWhoWaitConsultations (Long chiefId)throws NotFoundException;

    List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException;

    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException;


    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException;


}

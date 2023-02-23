package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface ReporterService {
    PatientViewAccountDTO getPatientDataOverTimeRange (String patientEmailId, LocalDate start, LocalDate stop)throws NotFoundException;

    List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (String doctorEmailId, LocalDate start, LocalDate stop)throws NotFoundException;

    List<PatientDTO> getPatientsWhoWaitConsultations ();

    List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (LocalDate start, LocalDate stop);

    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop);


    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop);


}

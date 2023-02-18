package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface MediDataVaultService {


    Map<String,Object> viewAccount(String patientEmailId) throws NotFoundException;

    PatientDTO signUp(PatientDTO patientDTO);

    boolean removeAccount(String patientEmailId) throws NotFoundException ;

    BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, String patientEmailId) throws NotFoundException ;

    DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, String patientEmailId) throws NotFoundException ;

    Double averageBgMeasurement(LocalDate start, LocalDate stop, String patientEmailId) throws NotFoundException ;

    Double averageDciMeasurement(LocalDate start, LocalDate stop, String patientEmailId) throws NotFoundException ;

    List<ConsultationDTO> getConsultations(String patientEmailId)throws NotFoundException ;

    BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long measurementId, String patientEmailId)throws NotFoundException ;

    DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO,Long measurementId, String patientEmailId)throws NotFoundException ;

    boolean deleteBgMeasurement(Long measurementId, String patientEmailId)throws NotFoundException ;

    boolean deleteDciMeasurement(Long measurementId, String patientEmailId) throws NotFoundException;

}
package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestParamException;
import gr.codehub.sacchon.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;


public interface MediDataVaultService {


    PatientViewAccountDTO viewAccount(String patientEmailId) throws NotFoundException;

    PatientDTO signUp(PatientDTO patientDTO);

    void removeAccount(String patientEmailId) throws NotFoundException ;

    BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, String patientEmailId) throws NotFoundException ;

    DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, String patientEmailId) throws NotFoundException ;

    Double averageBgMeasurement(LocalDate start, LocalDate stop, String patientEmailId) throws NotFoundException, BadRequestParamException;

    Double averageDciMeasurement(LocalDate start, LocalDate stop, String patientEmailId) throws NotFoundException,BadRequestParamException ;

    List<ConsultationDTO> getConsultations(String patientEmailId)throws NotFoundException ;

    BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long measurementId, String patientEmailId)throws NotFoundException ;

    DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO,Long measurementId, String patientEmailId)throws NotFoundException ;

    boolean deleteBgMeasurement(Long measurementId, String patientEmailId)throws NotFoundException ;

    boolean deleteDciMeasurement(Long measurementId, String patientEmailId) throws NotFoundException;

}
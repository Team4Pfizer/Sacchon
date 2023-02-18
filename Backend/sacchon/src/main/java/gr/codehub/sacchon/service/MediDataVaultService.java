package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;

import java.time.LocalDate;
import java.util.List;


public interface MediDataVaultService {


    PatientDTO viewAccount(String patientEmailId);

    PatientDTO signUp(PatientDTO patientDTO);

    boolean removeAccount(String patientEmailId);

    BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, String patientEmailId) throws RuntimeException;

    DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, String patientEmailId)throws RuntimeException;

    Double averageBgMeasurement(LocalDate start, LocalDate stop, String patientEmailId);

    Double averageDciMeasurement(LocalDate start, LocalDate stop, String patientEmailId);

    List<ConsultationDTO> getConsultations(String patientEmailId);

    BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, LocalDate measurementDate,String patientEmailId);

    DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO, LocalDate measurementDate, String patientEmailId);

    boolean deleteBgMeasurement(Long bgMeasurementId);

    boolean deleteDciMeasurement(Long dciMeasurementId);

}
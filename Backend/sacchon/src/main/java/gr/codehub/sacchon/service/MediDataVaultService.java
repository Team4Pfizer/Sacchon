package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;

import java.time.LocalDate;
import java.util.List;


public interface MediDataVaultService {
    Patient viewAccount(Long patientId);

    Patient signUp(Patient patient);

    boolean removeAccount(Long patientId);

    BgMeasurement addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long patientId) throws RuntimeException;

    DciMeasurement addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, Long patientId);

    Double averageBgMeasurement(LocalDate start, LocalDate stop, Long patientId);

    Double averageDciMeasurement(LocalDate start, LocalDate stop, Long patientId);

    List<Consultation> getConsultations(Long patientId);

    BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long patientId);

    DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO, Long patientId);

    boolean deleteBgMeasurement(Long bgMeasurementId);

    boolean deleteDciMeasurement(Long dciMeasurementId);

}
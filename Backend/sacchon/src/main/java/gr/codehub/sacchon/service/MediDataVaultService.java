package gr.codehub.sacchon.service;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.bgMeasurement;

import java.util.List;


public interface MediDataVaultService {
    public Patient viewAccount(Long patientId);
    public Patient signUp(Patient patient);

    public boolean removeAccount (Long patientId);

    public BgMeasurementDTO addBgMeasurement (BgMeasurementDTO bgMeasurementDTO, Long patientId);
    public DciMeasurementDTO addDciMeasurement (DciMeasurementDTO dciMeasurementDTO, Long patientId);

    public Double averageBgMeasurement (String start,String stop,Long patientId);

    public Double averageDciMeasurement (String start,String stop,Long patientId);

    public List<Consultation> getConsultations (Long patientId);

    public BgMeasurementDTO updateBgMeasurement (BgMeasurementDTO bgMeasurementDTO, Long patientId);

    public DciMeasurementDTO updateDciMeasurement (DciMeasurementDTO dciMeasurementDTO, Long patientId);

    public boolean deleteBgMeasurement (Long bgMeasurementId);

    public boolean deleteDciMeasurement (Long dciMeasurementId);











}

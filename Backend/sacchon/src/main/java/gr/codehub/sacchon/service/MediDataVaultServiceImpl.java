package gr.codehub.sacchon.service;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediDataVaultServiceImpl implements MediDataVaultService{
    @Override
    public Patient viewAccount(Long patientId) {
        return null;
    }

    @Override
    public Patient signUp(Patient patient) {
        return null;
    }

    @Override
    public boolean removeAccount(Long patientId) {
        return false;
    }

    @Override
    public BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long patientId) {
        return null;
    }

    @Override
    public DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, Long patientId) {
        return null;
    }

    @Override
    public Double averageBgMeasurement(String start, String stop, Long patientId) {
        return null;
    }

    @Override
    public Double averageDciMeasurement(String start, String stop, Long patientId) {
        return null;
    }

    @Override
    public List<Consultation> getConsultations(Long patientId) {
        return null;
    }

    @Override
    public BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long patientId) {
        return null;
    }

    @Override
    public DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO, Long patientId) {
        return null;
    }

    @Override
    public boolean deleteBgMeasurement(Long bgMeasurementId) {
        return false;
    }

    @Override
    public boolean deleteDciMeasurement(Long dciMeasurementId) {
        return false;
    }
}

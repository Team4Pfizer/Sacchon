package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MediDataVaultServiceImpl implements MediDataVaultService {

    private final PatientRepository patientRepository;

    @Override
    public Patient viewAccount(Long patientId) {
        return null;
    }

    @Override
    public Patient signUp(PatientDTO patientDTO) {
        return null;
    }

    @Override
    public boolean removeAccount(Long patientId) {
        return false;
    }

    @Override
    public BgMeasurement addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long patientId) throws RuntimeException {
        return null;
    }

    @Override
    public DciMeasurement addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, Long patientId) {
        return null;
    }

    @Override
    public Double averageBgMeasurement(LocalDate start, LocalDate stop, Long patientId) {
        return null;
    }

    @Override
    public Double averageDciMeasurement(LocalDate start, LocalDate stop, Long patientId) {
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

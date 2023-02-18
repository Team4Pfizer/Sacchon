package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.BgMeasurementRepository;
import gr.codehub.sacchon.repository.ConsulationRepository;
import gr.codehub.sacchon.repository.DciMeasurementRepository;
import gr.codehub.sacchon.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediDataVaultServiceImpl implements MediDataVaultService {

    private final PatientRepository patientRepository;
    private final BgMeasurementRepository bgMeasurementRepository;
    private final DciMeasurementRepository dciMeasurementRepository;

    private final ConsulationRepository consulationRepository;


    private Patient getPatient(String patientEmailId) throws RuntimeException {
        Optional<Patient> patientOptional = patientRepository.findByPatientEmailIdIgnoreCase(patientEmailId);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new RuntimeException("No patient with this ID: " + patientEmailId);
        }
    }
    public PatientDTO viewAccount(String patientEmailId){

        return new PatientDTO(getPatient(patientEmailId));
    }


    @Override
    public PatientDTO signUp(PatientDTO patientDTO) {
        Patient patient = patientRepository.save(patientDTO.toEntity());
        return new PatientDTO(patient);
    }

    @Override
    public boolean removeAccount(String patientEmailId) {
        if (patientRepository.findByPatientEmailIdIgnoreCase(patientEmailId).isPresent()){
            patientRepository.deleteByPatientEmailId(patientEmailId);
            return true;
        }else
            return false;

    }

    @Override
    public BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, String patientEmailId) throws RuntimeException {

        BgMeasurement savedBgMeasurement = bgMeasurementRepository.save(bgMeasurementDTO.toEntity(getPatient(patientEmailId)));
        return new BgMeasurementDTO(savedBgMeasurement);

    }

    @Override
    public DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, String patientEmailId)throws RuntimeException {
        DciMeasurement savedDciMeasurement = dciMeasurementRepository.save(dciMeasurementDTO.toEntity(getPatient(patientEmailId)));
        return new DciMeasurementDTO(savedDciMeasurement);
    }

    @Override
    public Double averageBgMeasurement(LocalDate start, LocalDate stop, String patientEmailId) {
        List<BgMeasurement> bgMeasurementList = bgMeasurementRepository
                .findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(start,stop,getPatient(patientEmailId));
        return bgMeasurementList.stream()
                .map(BgMeasurement::getBgMeasurementData)
                .mapToDouble(x->x)
                .sum()/bgMeasurementList.size();


    }

    @Override
    public Double averageDciMeasurement(LocalDate start, LocalDate stop, String patientEmailId) {
        List<DciMeasurement> dciMeasurementList =dciMeasurementRepository
                .findDciMeasurementByDciMeasurementDateIsBetweenAndPatient(start,stop,getPatient(patientEmailId));

        return dciMeasurementList.stream()
                .map(DciMeasurement::getDciMeasurementData)
                .mapToDouble(x->x)
                .sum()/dciMeasurementList.size();
    }

    @Override
    public List<ConsultationDTO> getConsultations(String patientEmailId) {
        Patient patient =getPatient(patientEmailId);
        List<Consultation> consultations = consulationRepository.findConsultationByPatient(patient);

        return consultations
                .stream()
                .map(ConsultationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, LocalDate measurementDate, String patientEmailId) {
        Patient patient = getPatient(patientEmailId);
        BgMeasurement bgMeasurement = bgMeasurementRepository.findBgMeasurementByDateAndPatient(measurementDate,patient);
        if (Objects.nonNull(bgMeasurementDTO.getBgMeasurementDate())){
            bgMeasurement.setBgMeasurementDate(bgMeasurementDTO.getBgMeasurementDate());
        }
        if (Objects.nonNull(bgMeasurementDTO.getBgMeasurementTime())){
            bgMeasurement.setBgMeasurementTime(bgMeasurementDTO.getBgMeasurementTime());
        }
        if (bgMeasurementDTO.getBgMeasurementData()!=0){
            bgMeasurement.setBgMeasurementData(bgMeasurementDTO.getBgMeasurementData());
        }
        return new BgMeasurementDTO(bgMeasurementRepository.save(bgMeasurement));

    }

    @Override
    public DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO, LocalDate measurementDate, String patientEmailId) {
        Patient patient = getPatient(patientEmailId);
        DciMeasurement dciMeasurement = dciMeasurementRepository.findBgMeasurementByDateAndPatient(measurementDate,patient);
        if (Objects.nonNull(dciMeasurementDTO.getDciMeasurementDate())){
            dciMeasurement.setDciMeasurementDate(dciMeasurementDTO.getDciMeasurementDate());
        }
        if (dciMeasurementDTO.getDciMeasurementData()!=0){
            dciMeasurement.setDciMeasurementData(dciMeasurementDTO.getDciMeasurementData());
        }
        return new DciMeasurementDTO(dciMeasurementRepository.save(dciMeasurement));


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

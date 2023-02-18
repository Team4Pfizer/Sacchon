package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.exception.BadRequestParamException;
import gr.codehub.sacchon.exception.NotFoundException;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediDataVaultServiceImpl implements MediDataVaultService {

    private final PatientRepository patientRepository;
    private final BgMeasurementRepository bgMeasurementRepository;
    private final DciMeasurementRepository dciMeasurementRepository;

    private final ConsulationRepository consulationRepository;


    private Patient getPatient(String patientEmailId)  throws NotFoundException{
        Optional<Patient> patientOptional = patientRepository.findByPatientEmailIdIgnoreCase(patientEmailId);
        if (patientOptional.isPresent()) {
            return patientOptional.get();
        } else {
            throw new NotFoundException("No patient with this Email: " + patientEmailId);
        }
    }
    public Map< String , Object > viewAccount(String patientEmailId) throws NotFoundException{
        Map< String, Object > viewAccountMap =new HashMap<>();
        Patient patient = getPatient(patientEmailId);

        viewAccountMap.put("patient",new PatientDTO(patient));
        viewAccountMap.put("bgMeasurements",bgMeasurementRepository
                .findBgMeasurementByPatient(patient)
                .stream()
                .map(BgMeasurementDTO::new)
                .collect(Collectors.toList()));

        viewAccountMap.put("dciMeasurements",dciMeasurementRepository
                .findDciMeasurementByPatient(patient)
                .stream()
                .map(DciMeasurementDTO::new)
                .collect(Collectors.toList()));


        return viewAccountMap;
    }


    @Override
    public PatientDTO signUp(PatientDTO patientDTO) {
        Patient patient = patientRepository.save(patientDTO.toEntity());
        return new PatientDTO(patient);
    }

    @Override
    public boolean removeAccount(String patientEmailId) throws NotFoundException{
        try {
            Patient patient = getPatient(patientEmailId);
            patientRepository.delete(patient);
            return true;
        }catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public BgMeasurementDTO addBgMeasurement(BgMeasurementDTO bgMeasurementDTO, String patientEmailId) throws NotFoundException {

        BgMeasurement savedBgMeasurement = bgMeasurementRepository.save(bgMeasurementDTO.toEntity(getPatient(patientEmailId)));
        return new BgMeasurementDTO(savedBgMeasurement);

    }

    @Override
    public DciMeasurementDTO addDciMeasurement(DciMeasurementDTO dciMeasurementDTO, String patientEmailId) throws NotFoundException{
        DciMeasurement savedDciMeasurement = dciMeasurementRepository.save(dciMeasurementDTO.toEntity(getPatient(patientEmailId)));
        return new DciMeasurementDTO(savedDciMeasurement);
    }

    @Override
    public Double averageBgMeasurement(LocalDate start, LocalDate stop, String patientEmailId)
            throws NotFoundException ,BadRequestParamException{
        if (stop.isAfter(start)) {
            List<BgMeasurement> bgMeasurementList = bgMeasurementRepository
                    .findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(start, stop, getPatient(patientEmailId));
            return bgMeasurementList.stream()
                    .map(BgMeasurement::getBgMeasurementData)
                    .mapToDouble(x -> x)
                    .sum() / bgMeasurementList.size();
        }else {
            throw new BadRequestParamException("The start date : "+start+" must be before end date : "+stop);
        }

    }

    @Override
    public Double averageDciMeasurement(LocalDate start, LocalDate stop, String patientEmailId)
            throws NotFoundException , BadRequestParamException {
        if (stop.isAfter(start)) {
            List<DciMeasurement> dciMeasurementList = dciMeasurementRepository
                    .findDciMeasurementByDciMeasurementDateIsBetweenAndPatient(start, stop, getPatient(patientEmailId));

            return dciMeasurementList.stream()
                    .map(DciMeasurement::getDciMeasurementData)
                    .mapToDouble(x -> x)
                    .sum() / dciMeasurementList.size();
        }else {
            throw new BadRequestParamException("The start date : "+start+" must be before end date : "+stop);
        }
    }

    @Override
    public List<ConsultationDTO> getConsultations(String patientEmailId) throws NotFoundException{
        Patient patient =getPatient(patientEmailId);
        List<Consultation> consultations = consulationRepository.findConsultationByPatient(patient);

        return consultations
                .stream()
                .map(ConsultationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BgMeasurementDTO updateBgMeasurement(BgMeasurementDTO bgMeasurementDTO, Long measurementId, String patientEmailId) throws NotFoundException{
        Patient patient = getPatient(patientEmailId);
        Optional<BgMeasurement> bgMeasurementOptional = bgMeasurementRepository.findBgMeasurementByIdAndPatient(measurementId,patient);
        if (bgMeasurementOptional.isPresent()) {
            BgMeasurement bgMeasurement = bgMeasurementOptional.get();

            if (Objects.nonNull(bgMeasurementDTO.getBgMeasurementDate())) {
                bgMeasurement.setBgMeasurementDate(bgMeasurementDTO.getBgMeasurementDate());
            }
            if (Objects.nonNull(bgMeasurementDTO.getBgMeasurementTime())) {
                bgMeasurement.setBgMeasurementTime(bgMeasurementDTO.getBgMeasurementTime());
            }
            if (bgMeasurementDTO.getBgMeasurementData() != 0) {
                bgMeasurement.setBgMeasurementData(bgMeasurementDTO.getBgMeasurementData());
            }
            return new BgMeasurementDTO(bgMeasurementRepository.save(bgMeasurement));
        }else
            throw new NotFoundException("No Blood glucose level measurement with this Email Id: " + patientEmailId+ " and this Measurement Id: "+measurementId);
    }

    @Override
    public DciMeasurementDTO updateDciMeasurement(DciMeasurementDTO dciMeasurementDTO,Long measurementId, String patientEmailId) throws NotFoundException{
        Patient patient = getPatient(patientEmailId);
        Optional<DciMeasurement> dciMeasurementOptional = dciMeasurementRepository.findBgMeasurementByIdAndPatient(measurementId,patient);
        if (dciMeasurementOptional.isPresent()) {
            DciMeasurement dciMeasurement = dciMeasurementOptional.get();
            if (Objects.nonNull(dciMeasurementDTO.getDciMeasurementDate())) {
                dciMeasurement.setDciMeasurementDate(dciMeasurementDTO.getDciMeasurementDate());
            }
            if (dciMeasurementDTO.getDciMeasurementData() != 0) {
                dciMeasurement.setDciMeasurementData(dciMeasurementDTO.getDciMeasurementData());
            }
            return new DciMeasurementDTO(dciMeasurementRepository.save(dciMeasurement));
        }else {
            throw new NotFoundException("No Daily Carb measurement with this Email Id: " + patientEmailId+ " and this Measurement Id: "+measurementId);
        }
    }

    @Override
    public boolean deleteBgMeasurement(Long measurementId, String patientEmailId) throws NotFoundException {
        Patient patient = getPatient(patientEmailId);
        Optional<BgMeasurement> bgMeasurementOptional = bgMeasurementRepository.findBgMeasurementByIdAndPatient(measurementId, patient);

        if (bgMeasurementOptional.isPresent()) {
            bgMeasurementRepository.delete(bgMeasurementOptional.get());
            return true;

        }else{
            return false;
        }
    }

    @Override
    public boolean deleteDciMeasurement(Long measurementId, String patientEmailId) throws NotFoundException {
        Patient patient = getPatient(patientEmailId);
        Optional<DciMeasurement> dciMeasurementOptional = dciMeasurementRepository.findBgMeasurementByIdAndPatient(measurementId,patient);
        if (dciMeasurementOptional.isPresent()){
            dciMeasurementRepository.delete(dciMeasurementOptional.get());
            return true;
        }else {
            return false;
        }

    }
}

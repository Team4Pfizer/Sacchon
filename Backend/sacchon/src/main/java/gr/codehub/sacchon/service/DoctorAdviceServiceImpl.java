package gr.codehub.sacchon.service;


import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.logger.CustomLoggerService;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorAdviceServiceImpl implements DoctorAdviceService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final Clock clock;
    private final CustomLoggerService logger;
    private final BgMeasurementRepository bgMeasurementRepository;
    private final DciMeasurementRepository dciMeasurementRepository;



    private Doctor getDoctor(String doctorEmailId) throws NotFoundException{
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        } else {
            Long id=logger.logError("No doctor with this Email: " + doctorEmailId);
            throw new NotFoundException("No doctor with this Email: " + doctorEmailId+". For more information the error Id is : "+id);
        }
    }
    @Override
    public DoctorViewAccountDTO viewAccount(String doctorEmailId) throws NotFoundException {
        Doctor doctor = getDoctor(doctorEmailId);

        return new DoctorViewAccountDTO(
                new DoctorDTO(doctor),
                consultationRepository.findSumOfConsultationsByDoctor(doctor),
                consultationRepository.findSumOfLastMonthConsultationsByDoctorAndDate(doctor,LocalDate.now(clock).minusMonths(1L))
        );
    }

    @Override
    public DoctorDTO signUp(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.save(doctorDTO.toEntity());
        logger.logInfo("Doctor profile with the email :"+ doctor.getDoctorEmailId()+" created");
        return new DoctorDTO(doctor);
    }

    @Override
    public void removeAccount(String doctorEmailId) throws NotFoundException {

        Doctor doctor = getDoctor(doctorEmailId);
        doctorRepository.delete(doctor);
        logger.logInfo("Doctor profile with the email :"+ doctor.getDoctorEmailId()+" deleted");
    }

    @Override
    public List<PatientDTO> availablePatients(String doctorEmailId)throws NotFoundException{
        Doctor doctor = getDoctor(doctorEmailId);
        List<PatientDTO> patientList = patientRepository
                .findPatientsHavingMoreThanMonthOfMeasurementsAndTheyAreAvailable(doctor.getDoctorId())
                .stream()
                .map(PatientDTO::new)
                .toList();

        return patientList;
    }

    @Override
    public ConsultationDTO consultPatient(ConsultationDTO consultationDTO,String doctorEmailId, Long patientId)throws NotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (patientOptional.isPresent() && doctorOptional.isPresent()){
            Patient patient = patientOptional.get();
            if (patient.getPatientsDoctor().getDoctorEmailId().equalsIgnoreCase(doctorEmailId)) {
                ConsultationDTO savedConsultationDTO = new ConsultationDTO(
                        consultationRepository.save(consultationDTO.toEntity(patient, LocalDate.now(clock))));
                patient.setPatientsDoctor(doctorOptional.get());
                patientRepository.save(patient);

                return savedConsultationDTO;
            }else{
                String message="No patient with this Id: " + patientId+" is availiable for doctor with this Email: " + doctorEmailId;
                Long id=logger.logError(message);
                throw new NotFoundException(message+". For more information the error Id is : "+id);
            }
        }
        String message="No doctor with this Email: " + doctorEmailId+ " or "+"No patient with this Id: " + patientId;
        Long id=logger.logError(message);
        throw new NotFoundException(message+". For more information the error Id is : "+id);
    }

    @Override
    public PatientForDoctorViewDTO patientProfile(String doctorEmailId, Long patientId) throws NotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Doctor doctor = getDoctor(doctorEmailId);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();

            if (patientOptional.get().getPatientsDoctor().equals(doctor)){
                return new PatientForDoctorViewDTO(patient,
                        consultationRepository.findConsultationByPatient(patient).stream().map(ConsultationDTO::new).toList(),
                        bgMeasurementRepository.findBgMeasurementByPatient(patient).stream().map(BgMeasurementDTO::new).toList(),
                        dciMeasurementRepository.findDciMeasurementByPatient(patient).stream().map(DciMeasurementDTO::new).toList()
                        );

            }else{
                String message = "The Credentials of the Patient are not connected to the doctor with EmailId : "+doctorEmailId;
                Long id=logger.logError(message+" Patient Id :"+patientId);
                throw new NotFoundException(message+". For more information the error Id is : "+id);
            }

        }else {
            String message ="No patient with this ID: " + patientId;
            Long id=logger.logError(message);
            throw new NotFoundException(message+". For more information the error Id is : "+id);
        }

    }

    @Override
    public ConsultationDTO updateConsultation(String doctorEmailId, Long patientId, ConsultationDTO consultationDTO) throws NotFoundException {
        Optional<Consultation> consultationOptional = consultationRepository.findById(consultationDTO.getConsultationId());

        if (consultationOptional.isPresent()) {
            Consultation consultation = consultationOptional.get();
            if (consultation.getPatient().getPatientId().equals(patientId)) {

                if (getDoctor(doctorEmailId).equals(consultation.getPatient().getPatientsDoctor())) {
                    Patient patient = patientRepository.findById(patientId).get();

                    if (Objects.nonNull(consultationDTO.getConsultationDosage())) {
                        consultation.setConsultationDosage(consultationDTO.getConsultationDosage());
                    }
                    if (Objects.nonNull(consultationDTO.getConsultationMedication())) {
                        consultation.setConsultationMedication(consultationDTO.getConsultationMedication());
                    }


                    consultation.setConsultationDate(LocalDate.now(clock));
                    ConsultationDTO savedConsultationDTO = new ConsultationDTO(consultationRepository.save(consultation));
                    patient.setAlarm(true);
                    patientRepository.save(patient);
                    return savedConsultationDTO;
                }

            } else {
                String message = "The Consultation doesn't belong to the patient that is provided";
                Long id = logger.logError(message + " : Patient provided ID :" + consultationOptional.get().getPatient().getPatientId()
                        + " the consultation patient ID :" + consultation.getPatient().getPatientId());
                throw new NotFoundException(message + ". For more information the error Id is : " + id);
            }
        }
        String message ="No consultation with this ID: "+consultationDTO.getConsultationId();
        Long id=logger.logError(message);
        throw new NotFoundException(message+". For more information the error Id is : "+id);

    }


}

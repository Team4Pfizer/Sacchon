package gr.codehub.sacchon.service;


import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
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


    private final BgMeasurementRepository bgMeasurementRepository;
    private final DciMeasurementRepository dciMeasurementRepository;



    private Doctor getDoctor(Long doctorId) throws NotFoundException{
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        } else {

            throw new NotFoundException("No doctor with this Id: " + doctorId);
        }
    }
    @Override
    public DoctorViewAccountDTO viewAccount(Long doctorId) throws NotFoundException {
        Doctor doctor = getDoctor(doctorId);

        return new DoctorViewAccountDTO(
                new DoctorDTO(doctor),
                consultationRepository.findSumOfConsultationsByDoctor(doctor),
                consultationRepository.findSumOfLastMonthConsultationsByDoctorAndDate(doctor,LocalDate.now(clock).minusMonths(1L))
        );
    }

    @Override
    public DoctorDTO signUp(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.save(doctorDTO.toEntity());
        return new DoctorDTO(doctor);
    }

    @Override
    public void removeAccount(Long doctorId) throws NotFoundException {

        Doctor doctor = getDoctor(doctorId);

        List<Patient> patients = patientRepository.findPatientByPatientsDoctor(doctor);

        for (Patient patient:patients){
            patient.setPatientsDoctor(null);
        }
        patientRepository.saveAll(patients);
        doctorRepository.delete(doctor);

    }

    @Override
    public List<PatientDTO> availablePatients(Long doctorId)throws NotFoundException{
        Doctor doctor = getDoctor(doctorId);
        List<PatientDTO> patientList = patientRepository
                .findPatientsHavingMoreThanMonthOfMeasurementsAndTheyAreAvailable(doctor.getDoctorId())
                .stream()
                .map(PatientDTO::new)
                .toList();

        return patientList;
    }

    @Override
    public ConsultationDTO consultPatient(ConsultationDTO consultationDTO,Long doctorId, Long patientId)throws NotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Doctor doctor = getDoctor(doctorId);
        if (patientOptional.isPresent()){
            Patient patient = patientOptional.get();

            ConsultationDTO savedConsultationDTO = new ConsultationDTO(
                    consultationRepository.save(consultationDTO.toEntity(patient, LocalDate.now(clock))));
            patient.setPatientsDoctor(doctor);
            patientRepository.save(patient);

            return savedConsultationDTO;

        }

        throw new NotFoundException("No patient with this Id: " + patientId);
    }

    @Override
    public PatientForDoctorViewDTO patientProfile(Long doctorId, Long patientId) throws NotFoundException,BadRequestException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Doctor doctor = getDoctor(doctorId);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();

            if ((patientOptional.get().getPatientsDoctor()!=null) && (patientOptional.get().getPatientsDoctor().equals(doctor))){
                return new PatientForDoctorViewDTO(patient,
                        consultationRepository.findConsultationByPatient(patient).stream().map(ConsultationDTO::new).toList(),
                        bgMeasurementRepository.findBgMeasurementByPatient(patient).stream().map(BgMeasurementDTO::new).toList(),
                        dciMeasurementRepository.findDciMeasurementByPatient(patient).stream().map(DciMeasurementDTO::new).toList()
                        );

            }else{

                throw new BadRequestException("The Credentials of the Patient are not connected to the doctor with Id : "+doctorId);
            }

        }else {
            throw new NotFoundException("No patient with this Id: " + patientId);
        }

    }

    @Override
    public ConsultationDTO updateConsultation(Long doctorId, Long patientId, ConsultationDTO consultationDTO) throws NotFoundException,BadRequestException {
        if (consultationDTO.getConsultationId()!=null) {
            Optional<Consultation> consultationOptional = consultationRepository.findById(consultationDTO.getConsultationId());

            if (consultationOptional.isPresent()) {
                Consultation consultation = consultationOptional.get();
                if (consultation.getPatient().getPatientId().equals(patientId)) {

                    if (getDoctor(doctorId).equals(consultation.getPatient().getPatientsDoctor())) {
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
                    } else {
                        throw new BadRequestException("The Patient isn't consulted by this doctor");
                    }

                }else {

                    throw new BadRequestException("The Consultation doesn't belong to the patient that is provided");
                }

            }else {

                throw new NotFoundException("No consultation with this ID: " + consultationDTO.getConsultationId());

            }
        }

        throw new BadRequestException("No consultation ID provided");

    }


}

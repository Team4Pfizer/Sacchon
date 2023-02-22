package gr.codehub.sacchon.service;


import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.dto.DoctorViewAccountDTO;
import gr.codehub.sacchon.dto.PatientForDoctorViewDTO;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.logger.CustomLoggerService;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.ConsultationRepository;
import gr.codehub.sacchon.repository.DoctorRepository;
import gr.codehub.sacchon.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorAdviceServiceImpl implements DoctorAdviceService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;
    private final Clock clock;

    private final CustomLoggerService logger;



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
    public List<PatientForDoctorViewDTO> availablePatients(String doctorEmailId)throws NotFoundException{
        Doctor doctor = getDoctor(doctorEmailId);
        List<PatientForDoctorViewDTO> patientList = patientRepository
                .findPatientsHavingMoreThanMonthOfMeasurementsAndTheyAreAvailable(doctor.getDoctorId())
                .stream()
                .map(PatientForDoctorViewDTO::new)
                .toList();

        return patientList;
    }

    @Override
    public ConsultationDTO consultPatient(ConsultationDTO consultationDTO,String doctorEmailId, Long patientId)throws NotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (patientOptional.isPresent() && doctorOptional.isPresent()){
            Patient patient = patientOptional.get();

            ConsultationDTO savedConsultationDTO = new ConsultationDTO(
                    consultationRepository.save(consultationDTO.toEntity(patient, LocalDate.now(clock))));
            patient.setPatientsDoctor(doctorOptional.get());
            patientRepository.save(patient);

            return savedConsultationDTO;
        }else{
            throw new NotFoundException("No doctor with this Email: " + doctorEmailId+ " or "+"No patient with this Id: " + patientId);

        }



    }


}
//availability
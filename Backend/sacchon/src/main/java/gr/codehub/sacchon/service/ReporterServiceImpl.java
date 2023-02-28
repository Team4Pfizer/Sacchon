package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.model.ChiefDoctor;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReporterServiceImpl implements ReporterService{

    private final PatientRepository patientRepository;
    private final BgMeasurementRepository bgMeasurementRepository;
    private final DciMeasurementRepository dciMeasurementRepository;
    private final DoctorRepository doctorRepository;
    private final ConsultationRepository consultationRepository;
    private final ChiefDoctorRepository chiefDoctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final Clock clock;

    private void getChiefDoctor(ChiefDoctorDTO chiefDoctorDTO) throws NotFoundException,BadRequestException {
        ChiefDoctor chiefDoctor = chiefDoctorRepository.findChiefDoctorByChiefDoctorEmailId(chiefDoctorDTO.getEmail());
        if (chiefDoctor==null){
            throw new NotFoundException("No Chief Doctor with this email : "+ chiefDoctorDTO.getEmail());
        }else{
            if(!passwordEncoder.matches(chiefDoctorDTO.getPassword(),chiefDoctor.getChiefDoctorPassword())){
                throw new BadRequestException("The password does not match the email id: "+chiefDoctorDTO.getEmail());
            }
        }
    }
    @Override
    public PatientViewAccountDTO getPatientDataOverTimeRange(Long patientId, LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO) throws NotFoundException,BadRequestException {
        getChiefDoctor(chiefDoctorDTO);
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()){
            Patient patient = patientOptional.get();
            return new PatientViewAccountDTO(
                    patient,
                    bgMeasurementRepository.findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(start,stop,patient).stream().map(BgMeasurementDTO::new).toList(),
                    dciMeasurementRepository.findDciMeasurementByDciMeasurementDateIsBetweenAndPatient(start,stop,patient).stream().map(DciMeasurementDTO::new).toList());
        }else{

            throw new NotFoundException("No patient with this Id: " + patientId);
        }
    }
    @Override
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange(Long doctorId, LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)
            throws NotFoundException,BadRequestException {
        getChiefDoctor(chiefDoctorDTO);
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()){
            Doctor doctor=doctorOptional.get();
            return consultationRepository.findSumOfConsultationsByDoctorAndBetweenDates(doctor,start,stop)
                    .stream()
                    .map(ConsultationDTO::new)
                    .toList();
        }else {
            throw new NotFoundException("No doctor with this Id: " + doctorId);

        }

    }
    @Override
    public List<PatientDTO> getPatientsWhoWaitConsultations(ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException,BadRequestException {
        getChiefDoctor(chiefDoctorDTO);

        return patientRepository.findPatientsWaitingConsultations(LocalDate.now(clock))
                .stream()
                .map(PatientDTO::new)
                .toList();
    }

    @Override
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange(LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)
                                                                                             throws NotFoundException,BadRequestException {

        getChiefDoctor(chiefDoctorDTO);
        List<Object[]> list = consultationRepository.findPatientAndNoOfConsultationsOverTimeRange(start,stop);


        return list
                .stream()
                .map(x->PatientAndNoOfConsultationsDTO.builder()
                        .patientId((Long) x[0])
                        .patientEmailId((String) x[1])
                        .totalConsultations((Integer) x[2])
                        .build())
                .toList();




    }

    @Override
    public List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)
            throws NotFoundException,BadRequestException{
        getChiefDoctor(chiefDoctorDTO);


        return patientRepository.findPatientsWithNoActivityOverTimePeriod(start,stop)
                .stream()
                .map(p->PatientDTO
                        .builder()
                        .patientId((Long)p[0])
                        .patientEmailId((String) p[1])
                        .patientFirstName((String) p[2])
                        .patientLastName((String) p[3])
                        .build())
                .toList();
    }

    @Override
    public List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,ChiefDoctorDTO chiefDoctorDTO)
            throws NotFoundException,BadRequestException {
        getChiefDoctor(chiefDoctorDTO);
        return doctorRepository.findDoctorsWithNoActivityOverTimePeriod(start,stop)
                .stream()
                .map(d->DoctorDTO.builder()
                        .doctorEmailId((String) d[0])
                        .doctorFirstName((String) d[1])
                        .doctorLastName((String) d[2]).build())
                .toList();

    }
}

package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.*;
import lombok.AllArgsConstructor;
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
    private final Clock clock;

    private void getChiefDoctor(Long chiefId) throws NotFoundException {
        if (chiefDoctorRepository.findById(chiefId).isEmpty()){
            throw new NotFoundException("No Chief Doctor with this Id : "+ chiefId);
        }
    }
    @Override
    public PatientViewAccountDTO getPatientDataOverTimeRange(Long patientId, LocalDate start, LocalDate stop,Long chiefId) throws NotFoundException {
        getChiefDoctor(chiefId);
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
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange(Long doctorId, LocalDate start, LocalDate stop,Long chiefId) throws NotFoundException {
        getChiefDoctor(chiefId);
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
    public List<PatientDTO> getPatientsWhoWaitConsultations(Long chiefId)throws NotFoundException {
        getChiefDoctor(chiefId);

        return patientRepository.findPatientsWaitingConsultations(LocalDate.now(clock))
                .stream()
                .map(PatientDTO::new)
                .toList();
    }

    @Override
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange(LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException {
        List<Object[]> list = consultationRepository.findPatientAndNoOfConsultationsOverTimeRange(start,stop);
        getChiefDoctor(chiefId);

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
    public List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,Long chiefId) throws NotFoundException{
        getChiefDoctor(chiefId);


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
    public List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop,Long chiefId)throws NotFoundException {
        getChiefDoctor(chiefId);
        return doctorRepository.findDoctorsWithNoActivityOverTimePeriod(start,stop)
                .stream()
                .map(d->DoctorDTO.builder()
                        .doctorEmailId((String) d[0])
                        .doctorFirstName((String) d[1])
                        .doctorLastName((String) d[2]).build())
                .toList();

    }
}

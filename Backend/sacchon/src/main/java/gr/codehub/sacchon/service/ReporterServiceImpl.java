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
    private final Clock clock;
    @Override
    public PatientViewAccountDTO getPatientDataOverTimeRange(String patientEmailId, LocalDate start, LocalDate stop) throws NotFoundException {
        Optional<Patient> patientOptional = patientRepository.findByPatientEmailIdIgnoreCase(patientEmailId);
        if (patientOptional.isPresent()){
            Patient patient = patientOptional.get();
            return new PatientViewAccountDTO(
                    patient,
                    bgMeasurementRepository.findBgMeasurementByBgMeasurementDateIsBetweenAndPatient(start,stop,patient).stream().map(BgMeasurementDTO::new).toList(),
                    dciMeasurementRepository.findDciMeasurementByDciMeasurementDateIsBetweenAndPatient(start,stop,patient).stream().map(DciMeasurementDTO::new).toList());
        }else{

            throw new NotFoundException("No patient with this Email: " + patientEmailId);
        }
    }
    @Override
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange(String doctorEmailId, LocalDate start, LocalDate stop) throws NotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (doctorOptional.isPresent()){
            Doctor doctor=doctorOptional.get();
            return consultationRepository.findSumOfConsultationsByDoctorAndBetweenDates(doctor,start,stop).stream().map(ConsultationDTO::new).toList();
        }else {
            throw new NotFoundException("No doctor with this Email: " + doctorEmailId);

        }

    }
    @Override
    public List<PatientDTO> getPatientsWhoWaitConsultations() {

        return patientRepository.findPatientsWaitingConsultations(LocalDate.now(clock))
                .stream()
                .map(PatientDTO::new)
                .toList();
    }

    @Override
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange(LocalDate start, LocalDate stop) {
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
    public List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop) {


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
    public List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop) {
        return doctorRepository.findDoctorsWithNoActivityOverTimePeriod(start,stop)
                .stream()
                .map(d->DoctorDTO.builder()
                        .doctorEmailId((String) d[0])
                        .doctorFirstName((String) d[1])
                        .doctorLastName((String) d[2]).build())
                .toList();

    }
}

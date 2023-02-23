package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.logger.CustomLoggerService;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
    private final CustomLoggerService logger;
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
            String message ="No patient with this Email: " + patientEmailId;
            Long id=logger.logError(message);
            throw new NotFoundException(message+". For more information the error Id is : "+id);
        }
    }
    @Override
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange(String doctorEmailId, LocalDate start, LocalDate stop) throws NotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (doctorOptional.isPresent()){
            Doctor doctor=doctorOptional.get();
            return consultationRepository.findSumOfConsultationsByDoctorAndBetweenDates(doctor,start,stop).stream().map(ConsultationDTO::new).toList();
        }else {
            String message ="No patient with this Email: " + doctorEmailId;
            Long id=logger.logError(message);
            throw new NotFoundException(message+". For more information the error Id is : "+id);

        }

    }
    @Override
    public List<PatientDTO> getPatientsWhoWaitConsultations() {
        return null;
    }

    @Override
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange(LocalDate start, LocalDate stop) {
        return null;
    }

    @Override
    public List<PatientDTO> getPatientsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop) {
        return null;
    }

    @Override
    public List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(LocalDate start, LocalDate stop) {
        return null;
    }
}

package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.ReporterService;
import gr.codehub.sacchon.validate.DateValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/reporter")
public class ReporterController {
    private final ReporterService reporterService;
    private final Clock clock;


    @GetMapping("/patient/dataovertime/{chiefId}/{patientId}")
    public PatientViewAccountDTO getPatientDataOverTimeRange (@PathVariable("patientId") Long patientId,
                                                              @Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO,
                                                       @RequestParam("start") String start,
                                                       @RequestParam("stop")  String stop)throws NotFoundException, BadRequestException {
        return reporterService.getPatientDataOverTimeRange(patientId,DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefDoctorDTO);

    }
    @GetMapping("/doctor/dataovertime/{chiefId}/{doctorId}")
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (@PathVariable("doctorId") Long doctorId,
                                                                       @Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO,
                                                                @RequestParam("start") String start,
                                                                @RequestParam("stop")  String stop)throws NotFoundException,BadRequestException{
        return reporterService.getDoctorsConsultationsOverTimeRange(doctorId,DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefDoctorDTO);
    }
    @GetMapping("patients/noconsultations/{chiefId}")
    public List<PatientDTO> getPatientsWhoWaitConsultations (@Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO)throws NotFoundException, BadRequestException{
        return reporterService.getPatientsWhoWaitConsultations(chiefDoctorDTO);
    }
    @GetMapping("/patients/consultations/{chiefId}")
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (@RequestParam("start") String start,
                                                                                              @Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO,
                                                                                              @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getPatientsWhoHaveBeenConsultedOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefDoctorDTO);
    }
    @GetMapping("/patients/withoutactivity/{chiefId}")
    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(@Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO,
                                                            @RequestParam("start") String start,
                                                            @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getPatientsWithNoActivityOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefDoctorDTO);
    }

    @GetMapping("/doctors/withoutactivity/{chiefId}")
    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(@Valid @RequestBody ChiefDoctorDTO chiefDoctorDTO,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getDoctorsWithNoActivityOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefDoctorDTO);

    }

}

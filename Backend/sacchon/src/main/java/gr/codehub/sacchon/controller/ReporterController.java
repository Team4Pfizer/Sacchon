package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.ReporterService;
import gr.codehub.sacchon.validate.DateValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/reporter")
public class ReporterController {
    private final ReporterService reporterService;


    @GetMapping("/patient/dataovertime/{patientId}")
    public PatientViewAccountDTO getPatientDataOverTimeRange (@PathVariable("patientId") Long patientId,
                                                       @RequestParam("start") String start,
                                                       @RequestParam("stop")  String stop)throws NotFoundException, BadRequestException {
        return reporterService.getPatientDataOverTimeRange(patientId,DateValidator.validateDate(start),DateValidator.validateDate(stop));

    }
    @GetMapping("/doctor/dataovertime/{doctorId}")
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (@PathVariable("doctorId") Long doctorId,
                                                                @RequestParam("start") String start,
                                                                @RequestParam("stop")  String stop)throws NotFoundException,BadRequestException{
        return reporterService.getDoctorsConsultationsOverTimeRange(doctorId,DateValidator.validateDate(start),DateValidator.validateDate(stop));
    }
    @GetMapping("patients/noconsultations")
    public List<PatientDTO> getPatientsWhoWaitConsultations (){
        return reporterService.getPatientsWhoWaitConsultations();
    }
    @GetMapping("/patients/consultations")
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (@RequestParam("start") String start,
                                                                                              @RequestParam("stop")  String stop)throws BadRequestException{
        return reporterService.getPatientsWhoHaveBeenConsultedOverTimeRange(DateValidator.validateDate(start),DateValidator.validateDate(stop));
    }
    @GetMapping("/patients/withoutactivity")
    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(@RequestParam("start") String start,
                                                            @RequestParam("stop")  String stop)throws BadRequestException{
        return reporterService.getPatientsWithNoActivityOverTimeRange(DateValidator.validateDate(start),DateValidator.validateDate(stop));
    }

    @GetMapping("/doctors/withoutactivity")
    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(@RequestParam("start") String start,
                                                          @RequestParam("stop")  String stop)throws BadRequestException{
        return reporterService.getDoctorsWithNoActivityOverTimeRange(DateValidator.validateDate(start),DateValidator.validateDate(stop));

    }

}

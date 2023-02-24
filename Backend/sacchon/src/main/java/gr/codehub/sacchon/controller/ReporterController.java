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


    @GetMapping("/patient/dataovertime/{patientEmailId}")
    public PatientViewAccountDTO getPatientDataOverTimeRange (@PathVariable("patientEmailId") String patientEmailId,
                                                       @RequestParam("start") String start,
                                                       @RequestParam("stop")  String stop)throws NotFoundException, BadRequestException {
        return reporterService.getPatientDataOverTimeRange(patientEmailId,DateValidator.validateDate(start),DateValidator.validateDate(stop));

    }
    @GetMapping("/doctor/dataovertime/{doctorEmailId}")
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (@PathVariable("doctorEmailId") String doctorEmailId,
                                                                @RequestParam("start") String start,
                                                                @RequestParam("stop")  String stop)throws NotFoundException,BadRequestException{
        return reporterService.getDoctorsConsultationsOverTimeRange(doctorEmailId,DateValidator.validateDate(start),DateValidator.validateDate(stop));
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

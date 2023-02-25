package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.ReporterService;
import gr.codehub.sacchon.validate.DateValidator;
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
                                                              @PathVariable("chiefId") Long chiefId,
                                                       @RequestParam("start") String start,
                                                       @RequestParam("stop")  String stop)throws NotFoundException, BadRequestException {
        return reporterService.getPatientDataOverTimeRange(patientId,DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefId);

    }
    @GetMapping("/doctor/dataovertime/{chiefId}/{doctorId}")
    public List<ConsultationDTO> getDoctorsConsultationsOverTimeRange (@PathVariable("doctorId") Long doctorId,
                                                                       @PathVariable("chiefId") Long chiefId,
                                                                @RequestParam("start") String start,
                                                                @RequestParam("stop")  String stop)throws NotFoundException,BadRequestException{
        return reporterService.getDoctorsConsultationsOverTimeRange(doctorId,DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefId);
    }
    @GetMapping("patients/noconsultations/{chiefId}")
    public List<PatientDTO> getPatientsWhoWaitConsultations (@PathVariable("chiefId") Long chiefId)throws NotFoundException{
        return reporterService.getPatientsWhoWaitConsultations(chiefId);
    }
    @GetMapping("/patients/consultations/{chiefId}")
    public List<PatientAndNoOfConsultationsDTO> getPatientsWhoHaveBeenConsultedOverTimeRange (@RequestParam("start") String start,
                                                                                              @PathVariable("chiefId") Long chiefId,
                                                                                              @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getPatientsWhoHaveBeenConsultedOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefId);
    }
    @GetMapping("/patients/withoutactivity/{chiefId}")
    List<PatientDTO> getPatientsWithNoActivityOverTimeRange(@PathVariable("chiefId") Long chiefId,
                                                            @RequestParam("start") String start,
                                                            @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getPatientsWithNoActivityOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefId);
    }

    @GetMapping("/doctors/withoutactivity/{chiefId}")
    List<DoctorDTO> getDoctorsWithNoActivityOverTimeRange(@PathVariable("chiefId") Long chiefId,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("stop")  String stop)throws BadRequestException,NotFoundException{
        return reporterService.getDoctorsWithNoActivityOverTimeRange(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),chiefId);

    }

}

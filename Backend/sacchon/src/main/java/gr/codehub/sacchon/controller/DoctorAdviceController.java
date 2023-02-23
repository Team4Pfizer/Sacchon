package gr.codehub.sacchon.controller;


import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.DoctorAdviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/doctoradvice")
public class DoctorAdviceController {

    private final DoctorAdviceService doctorAdviceService;

    @GetMapping("/myaccount/{doctorEmailId}")
    public DoctorViewAccountDTO viewAccount(@PathVariable("doctorEmailId") String doctorEmailId) throws NotFoundException {
        return doctorAdviceService.viewAccount(doctorEmailId);
    }

    @PostMapping("/signup")
    public DoctorDTO signUp(@RequestBody DoctorDTO doctorDTO) {
        return doctorAdviceService.signUp(doctorDTO);
    }

    @DeleteMapping("/delete/{doctorEmailId}")
    public void removeAccount(@PathVariable("doctorEmailId") String doctorEmailId) throws NotFoundException {
        doctorAdviceService.removeAccount(doctorEmailId);
    }

    @GetMapping("/availablepatients/{doctorEmailId}")
    public List<PatientDTO> availablePatients (@PathVariable("doctorEmailId") String doctorEmailId)throws NotFoundException{
        return doctorAdviceService.availablePatients(doctorEmailId);
    }

    @PostMapping("consult/{doctorEmailId}/{patientId}")
    public ConsultationDTO consultPatient (@RequestBody ConsultationDTO consultationDTO,
                                           @PathVariable("doctorEmailId") String emailId,
                                           @PathVariable("patientId") Long patientId)throws NotFoundException{
        return doctorAdviceService.consultPatient(consultationDTO,emailId,patientId);
    }

    @GetMapping("/patient/{doctorEmailId}/{patientId}")
    public PatientForDoctorViewDTO patientProfile (@PathVariable("doctorEmailId") String doctorEmailId,
                                                   @PathVariable("patientId") Long patientId)throws NotFoundException{
        return doctorAdviceService.patientProfile(doctorEmailId,patientId);
    }

    @PutMapping("/updateconsultation/{doctorEmailId}/{patientId}")
    public ConsultationDTO updateConsultation (@PathVariable("doctorEmailId") String doctorEmailId,
                                               @PathVariable("patientId") Long patientId,
                                               @RequestBody ConsultationDTO consultationDTO)throws NotFoundException{
        return doctorAdviceService.updateConsultation(doctorEmailId,patientId,consultationDTO);
    }

}
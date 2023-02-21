package gr.codehub.sacchon.controller;


import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.dto.PatientViewAccountDTO;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.DoctorAdviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("doctoradvice")
public class DoctorAdviceController {

    private final DoctorAdviceService doctorAdviceService;

    @GetMapping("/myaccount/{emailId}")
    public Map<String, Object> viewAccount(@PathVariable("emailId") String doctorEmailId) throws NotFoundException {
        return doctorAdviceService.viewAccount(doctorEmailId);
    }

    @PostMapping("/signup")
    public DoctorDTO signUp(@RequestBody DoctorDTO doctorDTO) {
        return doctorAdviceService.signUp(doctorDTO);
    }

    @DeleteMapping("/delete/{emailId}")
    public void removeAccount(@PathVariable("emailId") String doctorEmailId) throws NotFoundException {
        doctorAdviceService.removeAccount(doctorEmailId);
    }

    @GetMapping("/availablepatients/{emailId}")
    public List<PatientViewAccountDTO> availablePatients (@PathVariable("emailId") String doctorEmailId)throws NotFoundException{
        return doctorAdviceService.availablePatients(doctorEmailId);
    }

    @PostMapping("consult/{doctorEmailId}/{patientId}")
    public ConsultationDTO consultPatient (@RequestBody ConsultationDTO consultationDTO,
                                           @PathVariable("doctorEmailId") String emailId,
                                           @PathVariable("patientId") Long patientId)throws NotFoundException{
        return doctorAdviceService.consultPatient(consultationDTO,emailId,patientId);
    }


}
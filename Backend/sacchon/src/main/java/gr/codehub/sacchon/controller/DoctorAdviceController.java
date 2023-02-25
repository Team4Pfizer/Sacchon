package gr.codehub.sacchon.controller;


import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.DoctorAdviceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/doctoradvice")
public class DoctorAdviceController {

    private final DoctorAdviceService doctorAdviceService;



    @GetMapping("/myaccount/{doctorId}")
    public DoctorViewAccountDTO viewAccount(@PathVariable("doctorId")  Long doctorId) throws NotFoundException {
        return doctorAdviceService.viewAccount(doctorId);
    }

    @PostMapping("/signup")
    public DoctorDTO signUp(@Valid @RequestBody DoctorDTO doctorDTO) {
        return doctorAdviceService.signUp(doctorDTO);
    }

    @DeleteMapping("/delete/{doctorId}")
    public void removeAccount(@PathVariable("doctorId") Long doctorId) throws NotFoundException {
        doctorAdviceService.removeAccount(doctorId);
    }

    @GetMapping("/availablepatients/{doctorId}")
    public List<PatientDTO> availablePatients (@PathVariable("doctorId") Long doctorId)throws NotFoundException{
        return doctorAdviceService.availablePatients(doctorId);
    }

    @PostMapping("consult/{doctorId}/{patientId}")
    public ConsultationDTO consultPatient (@Valid @RequestBody ConsultationDTO consultationDTO,
                                           @PathVariable("doctorId") Long doctorId,
                                           @PathVariable("patientId") Long patientId)throws NotFoundException{
        return doctorAdviceService.consultPatient(consultationDTO,doctorId,patientId);
    }

    @GetMapping("/patient/{doctorId}/{patientId}")
    public PatientForDoctorViewDTO patientProfile (@PathVariable("doctorId") Long doctorId,
                                                   @PathVariable("patientId") Long patientId)throws NotFoundException, BadRequestException {
        return doctorAdviceService.patientProfile(doctorId,patientId);
    }

    @PutMapping("/updateconsultation/{doctorId}/{patientId}")
    public ConsultationDTO updateConsultation (@PathVariable("doctorId") Long doctorId,
                                               @PathVariable("patientId") Long patientId,
                                               @RequestBody ConsultationDTO consultationDTO)throws NotFoundException,BadRequestException{
        return doctorAdviceService.updateConsultation(doctorId,patientId,consultationDTO);
    }

}
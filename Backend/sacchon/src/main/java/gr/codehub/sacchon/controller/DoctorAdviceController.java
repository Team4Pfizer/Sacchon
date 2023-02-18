package gr.codehub.sacchon.controller;


import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.DoctorAdviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/doctoradvice")
public class DoctorAdviceController {

    private final DoctorAdviceService doctorAdviceService;

    @GetMapping("/myaccount/{emailId}")
    public Map<String,Object> viewAccount(@PathVariable("emailId") String doctorEmailId) throws NotFoundException {
        return doctorAdviceService.viewAccount(doctorEmailId);
    }

    @PostMapping("/signup")
    public DoctorDTO signUp (@RequestBody DoctorDTO doctorDTO){
        return doctorAdviceService.signUp(doctorDTO);
    }


}

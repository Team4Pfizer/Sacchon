package gr.codehub.sacchon.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    // just for testing
    @GetMapping("/doctor")
    public String getDoctor(){
        return "Doctor";
    }

}

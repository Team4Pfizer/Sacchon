package gr.codehub.sacchon.controller;
/*
    * This class is responsible for the BgMeasurementController
    * it will return a list with all the BgMeasurements
    * it will return a list with all the BgMeasurements of a specific patient
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BgMeasurementController {

    @GetMapping("/bg")
    public String getBg(){
        return "Bg";
    }
}

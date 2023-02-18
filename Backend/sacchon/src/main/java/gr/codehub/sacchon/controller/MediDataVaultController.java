package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.exception.BadRequestParamException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.MediDataVaultService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MediDataVaultController {

    private MediDataVaultService mediDataVaultService;

    @GetMapping("/myaccount/{emailId}")
    public Map<String,Object> viewAccount(@PathVariable("emailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.viewAccount(patientEmailId);
    }

    @PostMapping("/signup")
    public PatientDTO signUp (@RequestBody PatientDTO patientDTO){
        return mediDataVaultService.signUp(patientDTO);
    }

    @DeleteMapping("/delete/{emailId}")
    public boolean removeAccount(@PathVariable("emailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.removeAccount(patientEmailId);
    }

    @PostMapping("/bgmeasurement/{emailId}")
    public BgMeasurementDTO addBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                          @PathVariable("emailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addBgMeasurement(bgMeasurementDTO,patientEmailId);
    }

    @PostMapping("/dcimeasurement/{emailId}")
    public DciMeasurementDTO addDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                            @PathVariable("emailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addDciMeasurement(dciMeasurementDTO,patientEmailId);
    }


    @GetMapping("/averagebgmeasurements/{emailId}")
    public Double averageBgMeasurement(
            @PathVariable("emailId") String patientEmailId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stop) throws NotFoundException, BadRequestParamException {


        return mediDataVaultService.averageBgMeasurement(start,stop,patientEmailId);

    }
    @GetMapping("/averagedcimeasurements/{emailId}")
    public Double averageDciMeasurement(
            @PathVariable("emailId") String patientEmailId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stop) throws NotFoundException,BadRequestParamException {


        return mediDataVaultService.averageDciMeasurement(start, stop, patientEmailId);


    }

    @GetMapping("/consultations/{emailId}")
    public List<ConsultationDTO> getConsultations(@PathVariable(name="emailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.getConsultations(patientEmailId);
    }

    @PutMapping("/bgmeasurement/{emailId}/{measurementId}")
    public BgMeasurementDTO updateBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                                @PathVariable(name="measurementId")Long measurementId,
                                                @PathVariable(name="emailId") String patientEmailId) throws NotFoundException {
        return  mediDataVaultService.updateBgMeasurement(bgMeasurementDTO, measurementId,patientEmailId);
    }

    @PutMapping("/dcimeasurement/{emailId}/{measurementId}")
    public DciMeasurementDTO updateDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                                  @PathVariable(name="emailId") String emailId,
                                                  @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {
        return  mediDataVaultService.updateDciMeasurement(dciMeasurementDTO, measurementId, emailId);
    }

    @DeleteMapping("/bgmeasurement/{emailId}/{measurementId}")
    public boolean deleteBgMeasurement(@PathVariable(name="emailId") String emailId,
                                       @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {

        return  mediDataVaultService.deleteBgMeasurement(measurementId,emailId);
    }

    @DeleteMapping("/dcimeasurement/{emailId}/{measurementId}")
    public boolean deleteDciMeasurement(@PathVariable(name="emailId") String emailId,
                                        @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {
        return  mediDataVaultService.deleteDciMeasurement(measurementId, emailId);
    }
}

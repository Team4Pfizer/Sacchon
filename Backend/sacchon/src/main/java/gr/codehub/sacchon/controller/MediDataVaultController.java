package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestParamException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.MediDataVaultService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/medidatavault")
public class MediDataVaultController {

    private MediDataVaultService mediDataVaultService;

    @GetMapping("/myaccount/{patientEmailId}")
    public PatientViewAccountDTO viewAccount(@PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.viewAccount(patientEmailId);
    }

    @PostMapping("/signup")
    public PatientDTO signUp (@RequestBody PatientDTO patientDTO){
        return mediDataVaultService.signUp(patientDTO);
    }

    @DeleteMapping("/delete/{patientEmailId}")
    public void removeAccount(@PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        mediDataVaultService.removeAccount(patientEmailId);
    }

    @PostMapping("/bgmeasurement/{patientEmailId}")
    public BgMeasurementDTO addBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                          @PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addBgMeasurement(bgMeasurementDTO,patientEmailId);
    }

    @PostMapping("/dcimeasurement/{patientEmailId}")
    public DciMeasurementDTO addDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                            @PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addDciMeasurement(dciMeasurementDTO,patientEmailId);
    }


    @GetMapping("/averagebgmeasurements/{patientEmailId}")
    public Double averageBgMeasurement(
            @PathVariable("patientEmailId") String patientEmailId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stop) throws NotFoundException, BadRequestParamException {


        return mediDataVaultService.averageBgMeasurement(start,stop,patientEmailId);

    }
    @GetMapping("/averagedcimeasurements/{patientEmailId}")
    public Double averageDciMeasurement(
            @PathVariable("patientEmailId") String patientEmailId,
            @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate stop) throws NotFoundException,BadRequestParamException {


        return mediDataVaultService.averageDciMeasurement(start, stop, patientEmailId);


    }

    @GetMapping("/consultations/{patientEmailId}")
    public List<ConsultationDTO> getConsultations(@PathVariable(name="patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.getConsultations(patientEmailId);
    }

    @PutMapping("/bgmeasurement/{patientEmailId}/{measurementId}")
    public BgMeasurementDTO updateBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                                @PathVariable(name="measurementId")Long measurementId,
                                                @PathVariable(name="patientEmailId") String patientEmailId) throws NotFoundException {
        return  mediDataVaultService.updateBgMeasurement(bgMeasurementDTO, measurementId,patientEmailId);
    }

    @PutMapping("/dcimeasurement/{patientEmailId}/{measurementId}")
    public DciMeasurementDTO updateDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                                  @PathVariable(name="patientEmailId") String emailId,
                                                  @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {
        return  mediDataVaultService.updateDciMeasurement(dciMeasurementDTO, measurementId, emailId);
    }

    @DeleteMapping("/bgmeasurement/{patientEmailId}/{measurementId}")
    public boolean deleteBgMeasurement(@PathVariable(name="patientEmailId") String emailId,
                                       @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {

        return  mediDataVaultService.deleteBgMeasurement(measurementId,emailId);
    }

    @DeleteMapping("/dcimeasurement/{patientEmailId}/{measurementId}")
    public boolean deleteDciMeasurement(@PathVariable(name="patientEmailId") String emailId,
                                        @PathVariable(name="measurementId")Long measurementId) throws NotFoundException {
        return  mediDataVaultService.deleteDciMeasurement(measurementId, emailId);
    }
}

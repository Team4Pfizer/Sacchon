package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.*;
import gr.codehub.sacchon.exception.BadRequestException;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.service.MediDataVaultService;
import gr.codehub.sacchon.validate.DateValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/medidatavault")
public class MediDataVaultController {

    private final MediDataVaultService mediDataVaultService;
    private final Clock clock;

    @GetMapping("/myaccount/{patientEmailId}")
    public PatientViewAccountDTO viewAccount(@PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.viewAccount(patientEmailId);
    }

    @PostMapping("/signup")
    public PatientDTO signUp (@Valid @RequestBody PatientDTO patientDTO){
        return mediDataVaultService.signUp(patientDTO);
    }

    @DeleteMapping("/delete/{patientEmailId}")
    public void removeAccount(@PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        mediDataVaultService.removeAccount(patientEmailId);
    }

    @PostMapping("/bgmeasurement/{patientEmailId}")
    public BgMeasurementDTO addBgMeasurement(@Valid @RequestBody BgMeasurementDTO bgMeasurementDTO,
                                          @PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addBgMeasurement(bgMeasurementDTO,patientEmailId);
    }

    @PostMapping("/dcimeasurement/{patientEmailId}")
    public DciMeasurementDTO addDciMeasurement(@Valid @RequestBody DciMeasurementDTO dciMeasurementDTO,
                                            @PathVariable("patientEmailId") String patientEmailId) throws NotFoundException {
        return mediDataVaultService.addDciMeasurement(dciMeasurementDTO,patientEmailId);

    }


    @GetMapping("/averagebgmeasurements/{patientEmailId}")
    public Double averageBgMeasurement(
            @PathVariable("patientEmailId") String patientEmailId,
            @RequestParam("start")  String start,
            @RequestParam("stop") String stop) throws NotFoundException, BadRequestException {


        return mediDataVaultService.averageBgMeasurement(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock),patientEmailId);

    }
    @GetMapping("/averagedcimeasurements/{patientEmailId}")
    public Double averageDciMeasurement(
            @PathVariable("patientEmailId") String patientEmailId,
            @RequestParam("start") String start,
            @RequestParam("stop") String stop) throws NotFoundException, BadRequestException {


        return mediDataVaultService.averageDciMeasurement(DateValidator.validateDate(start,clock),DateValidator.validateDate(stop,clock), patientEmailId);


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

package gr.codehub.sacchon.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.ConsultationDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.service.MediDataVaultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class MediDataVaultController {

    private MediDataVaultService mediDataVaultService;

    @GetMapping("/myaccount/{emailId}")
    public PatientDTO viewAccount(@PathVariable("emailId") String patientEmailId){
        return mediDataVaultService.viewAccount(patientEmailId);
    }

    @PostMapping("/signup")
    public PatientDTO signUp (@RequestBody PatientDTO patientDTO){
        return mediDataVaultService.signUp(patientDTO);
    }

    @DeleteMapping("/delete/{emailId}")
    public boolean removeAccount(@PathVariable("emailId") String patientEmailId){
        return mediDataVaultService.removeAccount(patientEmailId);
    }

    @PostMapping("/bgmeasurement/{emailId}")
    public BgMeasurementDTO addBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                          @PathVariable("emailId") String patientEmailId){
        return mediDataVaultService.addBgMeasurement(bgMeasurementDTO,patientEmailId);
    }

    @PostMapping("/dcimeasurement/{emailId}")
    public DciMeasurementDTO addDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                            @PathVariable("emailId") String patientEmailId){
        return mediDataVaultService.addDciMeasurement(dciMeasurementDTO,patientEmailId);
    }


    @GetMapping("/averageBgMeasurements/{emailId}")
    public Double averageBgMeasurement(
            @PathVariable("emailId") String patientEmailId,
            @RequestParam("start") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate stop){


        return mediDataVaultService.averageBgMeasurement(start,stop,patientEmailId);

    }
    @GetMapping("/averageDciMeasurements/{emailId}")
    public Double averageDciMeasurement(
            @PathVariable("emailId") String patientEmailId,
            @RequestParam("start") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("stop") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate stop) {


        return mediDataVaultService.averageDciMeasurement(start, stop, patientEmailId);


    }

    @GetMapping("/consultations/{emailId}")
    public List<ConsultationDTO> getConsultations(@PathVariable(name="emailId") String patientEmailId){
        return mediDataVaultService.getConsultations(patientEmailId);
    }

    @PutMapping("/bgmeasurement/{emailId}/{measurementDate}")
    public BgMeasurementDTO updateBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO,
                                                @PathVariable(name="measurementDate") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate measurementDate,
                                                @PathVariable(name="emailId") String patientEmailId){
        return  mediDataVaultService.updateBgMeasurement(bgMeasurementDTO, measurementDate,patientEmailId);
    }

    @PutMapping("/dcimeasurement/{emailId}/{measurementDate}")
    public DciMeasurementDTO updateDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO,
                                                  @PathVariable(name="emailId") String emailId,
                                                  @PathVariable(name="measurementDate")@JsonFormat(pattern = "yyyy-MM-dd") LocalDate measurementDate){
        return  mediDataVaultService.updateDciMeasurement(dciMeasurementDTO, measurementDate, emailId);
    }

    @DeleteMapping("/Patient/{id}/bgMeasurement/{bgMeasurementId}")
    public boolean deleteBgMeasurement(@PathVariable(name="bgMeasurementId") long bgMeasurementId){
        return  mediDataVaultService.deleteBgMeasurement(bgMeasurementId);
    }

    @DeleteMapping("/Patient/{id}/dciMeasurement/{dciMeasurementId}")
    public boolean deleteDciMeasurement(@PathVariable(name="dciMeasurementId") long dciMeasurementId){
        return  mediDataVaultService.deleteDciMeasurement(dciMeasurementId);
    }
}

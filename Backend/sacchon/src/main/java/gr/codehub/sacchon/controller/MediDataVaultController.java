package gr.codehub.sacchon.controller;

import gr.codehub.sacchon.dto.BgMeasurementDTO;
import gr.codehub.sacchon.dto.DciMeasurementDTO;
import gr.codehub.sacchon.dto.PatientDTO;
import gr.codehub.sacchon.model.BgMeasurement;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.DciMeasurement;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.service.MediDataVaultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class MediDataVaultController {

    private MediDataVaultService mediDataVaultService;

    @GetMapping("/Patient/{id}")
    public Patient viewAccount(@PathVariable(name="id") long id){
       return  mediDataVaultService.viewAccount(id);
    }

    @PostMapping("/Patient")
    public Patient signUp(@RequestBody PatientDTO patientDTO){
        return  mediDataVaultService.signUp(patientDTO);
    }

    @DeleteMapping("/Patient/{id}")
    public boolean removeAccount(@PathVariable(name="id") long id){
        return  mediDataVaultService.removeAccount(id);
    }

    @PostMapping("/Patient/{id}/bgMeasurement")
    public BgMeasurement addBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO, @PathVariable(name="id") long id){
        return  mediDataVaultService.addBgMeasurement(bgMeasurementDTO, id);
    }

    @PostMapping("/Patient/{id}/dciMeasurement")
    public DciMeasurement addDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO, @PathVariable(name="id") long id){
        return  mediDataVaultService.addDciMeasurement(dciMeasurementDTO, id);
    }

    @GetMapping("/Patient/{id}/bgMeasurement/average")
    public Double averageBgMeasurement(@RequestParam(name="start") LocalDate start, @RequestParam(name="stop") LocalDate stop, @PathVariable(name="id") long id){
        return  mediDataVaultService.averageBgMeasurement(start, stop, id);
    }

    @GetMapping("/Patient/{id}/dciMeasurement/average")
    public Double averageDciMeasurement(@RequestParam(name="start") LocalDate start, @RequestParam(name="stop") LocalDate stop, @PathVariable(name="id") long id){
        return  mediDataVaultService.averageDciMeasurement(start, stop, id);
    }

    @GetMapping("/Patient/{id}/consultations")
    public List<Consultation> getConsultations(@PathVariable(name="id") long id){
        return  mediDataVaultService.getConsultations(id);
    }

    @PutMapping("/Patient/{id}/bgMeasurement")
    public BgMeasurementDTO updateBgMeasurement(@RequestBody BgMeasurementDTO bgMeasurementDTO, @PathVariable(name="id") long id){
        return  mediDataVaultService.updateBgMeasurement(bgMeasurementDTO, id);
    }

    @PutMapping("/Patient/{id}/dciMeasurement")
    public DciMeasurementDTO updateDciMeasurement(@RequestBody DciMeasurementDTO dciMeasurementDTO, @PathVariable(name="id") long id){
        return  mediDataVaultService.updateDciMeasurement(dciMeasurementDTO, id);
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

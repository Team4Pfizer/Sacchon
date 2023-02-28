package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private Long consultationId;

    @Positive
    private Double consultationDosage ;
    @NotEmpty(message = "Medication Data is a required field")
    private String consultationMedication ;

    private LocalDate consultationDate;

    public ConsultationDTO(Consultation consultation){
        this.consultationDosage=consultation.getConsultationDosage();
        this.consultationMedication=consultation.getConsultationMedication();
        this.consultationDate=consultation.getConsultationDate();
        this.consultationId=consultation.getConsultationId();

    }


    public Consultation toEntity(Patient patient,LocalDate creationDate){
        return Consultation
                .builder()
                .patient(patient)
                .consultationMedication(this.consultationMedication)
                .consultationDosage(this.consultationDosage)
                .consultationDate(creationDate)
                .build();
    }




}

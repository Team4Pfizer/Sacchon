package gr.codehub.sacchon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDTO {
    private int consultationDosage ;

    private String consultationMedication ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate consultationDate;

    public ConsultationDTO(Consultation consultation){
        this.consultationDosage=consultation.getConsultationDosage();
        this.consultationMedication=consultation.getConsultationMedication();
        this.consultationDate=consultation.getConsultationDate();
    }

    public Consultation toEntity(Patient patient){
        return Consultation
                .builder()
                .patient(patient)
                .consultationMedication(this.consultationMedication)
                .consultationDosage(this.consultationDosage)
                .consultationDate(this.consultationDate)
                .build();
    }




}

package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@Table(name="CONSULTATIONS")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private String message ; // the consultation message
    private LocalDate consultationOfMonth; // not sure if valid



    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")

    private Patient patient; // the patient that the consultation is abou

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // the doctor that the consultation is abou


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consultation that = (Consultation) o;

        return Objects.equals(consultationId, that.consultationId);
    }

    @Override
    public int hashCode() {
        return consultationId != null ? consultationId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "consultationId=" + consultationId +
                ", message='" + message + '\'' +
                ", consultationOfMonth=" + consultationOfMonth +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}

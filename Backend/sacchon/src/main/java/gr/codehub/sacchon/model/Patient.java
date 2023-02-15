package gr.codehub.sacchon.model;


import com.sun.source.doctree.DocTree;
import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId ;

    private String firstName ;
    private String lastName ;


    private String userEmail ;
    private String userPassword ;
    // has to be hashed in the db

    private Date registrationDate;

    private int userAge ;

    private boolean hasConsultant; // if the patient has or has no consultant

    //---

    @ManyToOne // many patients can have one doctor
    @JoinColumn(name = "doctorId")
    private Doctor doctor; // the patient's doctor

    @OneToMany(mappedBy = "patient")
    private Set<Consultation> consultations = new HashSet<>(); // the patient's consultations

    @OneToMany(mappedBy = "patient")
    private Set<BgMeasurement> bgMeasurements = new HashSet<>(); // the patient's bg measurements

    @OneToMany
    private Set<DciMeasurement> dciMeasurements = new HashSet<>(); // the patient's dci measurements


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        return Objects.equals(patientId, patient.patientId);
    }

    @Override
    public int hashCode() {
        return patientId != null ? patientId.hashCode() : 0;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", registrationDate=" + registrationDate +
                ", userAge=" + userAge +
                ", hasConsultant=" + hasConsultant +
                ", doctor=" + doctor +
                ", consultations=" + consultations +
                ", bgMeasurements=" + bgMeasurements +
                ", dciMeasurements=" + dciMeasurements +
                '}';
    }
}

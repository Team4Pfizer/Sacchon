package gr.codehub.sacchon.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="DOCTORS")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId ;
    private String userEmail ;
    private String userPassword;
    private String firstName;
    private String lastName;


    @OneToMany(mappedBy = "doctor")
    private Set<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private Set<Consultation> consultations;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        return Objects.equals(doctorId, doctor.doctorId);
    }

    @Override
    public int hashCode() {
        return doctorId != null ? doctorId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patients=" + patients +
                ", consultations=" + consultations +
                '}';
    }
}

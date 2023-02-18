package gr.codehub.sacchon.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CHIEF_DOCTORS")
public class ChiefDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chiefDoctorId;
    @Column(unique = true)
    private String chiefDoctorEmailId;
    private String chiefDoctorFirstName;
    private String chiefDoctorLastName;




}

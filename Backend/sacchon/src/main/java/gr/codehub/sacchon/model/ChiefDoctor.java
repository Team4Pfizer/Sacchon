package gr.codehub.sacchon.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
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
    @NotNull
    @NotEmpty
    private String chiefDoctorPassword;




}

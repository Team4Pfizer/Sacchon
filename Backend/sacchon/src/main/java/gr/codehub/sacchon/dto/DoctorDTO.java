package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Doctor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    @Email
    @NotNull
    private String doctorEmailId ;
    @NotNull
    @NotEmpty
    private String doctorFirstName;
    @NotNull
    @NotEmpty
    private String doctorLastName;

    public DoctorDTO(Doctor doctor){
        this.doctorEmailId=doctor.getDoctorEmailId();
        this.doctorFirstName=doctor.getDoctorFirstName();
        this.doctorLastName=doctor.getDoctorLastName();
    }

    public Doctor toEntity(){
        return Doctor.builder()
                .doctorEmailId(this.doctorEmailId)
                .doctorFirstName(this.doctorFirstName)
                .doctorLastName(this.doctorLastName)
                .build();
    }

}

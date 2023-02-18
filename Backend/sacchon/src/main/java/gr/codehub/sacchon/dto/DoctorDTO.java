package gr.codehub.sacchon.dto;

import gr.codehub.sacchon.model.Doctor;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String doctorEmailId ;
//    private String userPassword;
    private String doctorFirstName;
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

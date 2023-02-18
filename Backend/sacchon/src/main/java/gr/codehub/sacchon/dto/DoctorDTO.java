package gr.codehub.sacchon.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String userEmail ;
//    private String userPassword;
    private String firstName;
    private String lastName;

}

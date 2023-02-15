package gr.codehub.sacchon.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private String userEmail ;
//    private String userPassword;
    private String firstName;
    private String lastName;

}

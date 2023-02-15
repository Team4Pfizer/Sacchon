package gr.codehub.sacchon.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private String firstName ;
    private String lastName ;
    private String userEmail ;
    private LocalDate registrationDate;
    private int userAge ;


}

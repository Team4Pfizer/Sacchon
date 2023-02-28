package gr.codehub.sacchon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChiefDoctorDTO {

    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotEmpty
    @NotNull
    @Length(min = 4, max = 15)
    private String password;
}

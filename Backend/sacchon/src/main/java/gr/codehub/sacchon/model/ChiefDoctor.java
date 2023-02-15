package gr.codehub.sacchon.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ChiefDoctor {

    @Id
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;


}

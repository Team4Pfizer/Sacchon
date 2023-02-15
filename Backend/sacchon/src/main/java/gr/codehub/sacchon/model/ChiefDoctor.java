package gr.codehub.sacchon.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;


    @Override
    public String toString() {
        return "ChiefDoctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

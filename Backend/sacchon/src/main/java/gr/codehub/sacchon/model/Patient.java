package gr.codehub.sacchon.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Patient {

    @Id
    private Long id ;
    private String firstname ;
    private String lastname ;

    private Date registrationDate;

    private int age ;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

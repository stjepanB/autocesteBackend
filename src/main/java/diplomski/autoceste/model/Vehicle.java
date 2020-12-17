package diplomski.autoceste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;
    private String plate;


}

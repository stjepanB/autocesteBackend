package diplomski.autoceste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class User {


    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private Long oib;
    private String address;
    private String emailAddress;

    @OneToMany
    private List<Invoice> invoices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package diplomski.autoceste.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

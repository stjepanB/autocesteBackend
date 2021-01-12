package diplomski.autoceste.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VehicleDiscountLabel {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private VehicleCategory category;
    @Column(nullable = false)
    private VehicleParametersLabel parametersLabel;
    private ParameterOperation operation;
    private Double value;



}

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
    private String parametersLabel;
    private ParameterOperation operation;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public String getParametersLabel() {
        return parametersLabel;
    }

    public void setParametersLabel(String parametersLabel) {
        this.parametersLabel = parametersLabel;
    }

    public ParameterOperation getOperation() {
        return operation;
    }

    public void setOperation(ParameterOperation operation) {
        this.operation = operation;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

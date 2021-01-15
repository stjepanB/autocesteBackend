package diplomski.autoceste.forms;

import diplomski.autoceste.models.ParameterOperation;
import diplomski.autoceste.models.VehicleCategory;
import diplomski.autoceste.models.VehicleDiscountLabel;

public class VehicleDiscountLabelDto {

    private String name;
    private String operation;
    private String paramType;
    private String value;
    private String vehicleCategory;

    public VehicleDiscountLabelDto() {
    }

    public VehicleDiscountLabelDto(VehicleDiscountLabel discountLabel) {
        this.name = discountLabel.getName();
        this.operation = discountLabel.getOperation().toString();
        this.value = discountLabel.getValue() == null ? null : discountLabel.getValue().toString();
        this.paramType = discountLabel.getParametersLabel();
        this.vehicleCategory = discountLabel.getCategory().toString();
    }

    public VehicleDiscountLabel toVehicleDiscountLabel() {
        VehicleDiscountLabel v = new VehicleDiscountLabel();
        v.setCategory(VehicleCategory.valueOf(vehicleCategory));
        v.setName(name);
        v.setParametersLabel(paramType);
        v.setOperation(ParameterOperation.valueOf(operation.toUpperCase()));
        try {
            v.setValue(Double.parseDouble(value));
        } catch (NumberFormatException e) {
        }
        return v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }
}

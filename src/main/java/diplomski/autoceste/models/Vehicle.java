package diplomski.autoceste.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String plate;
    @Enumerated(EnumType.STRING)
    private VehicleCategory category;
    private String color;
    private String manufacturer;
    private String type;
    private Integer maxWeightWithCargo;
    private Integer height;
    private Boolean hasGreenCertificate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "private_user_id")
    @JsonIgnore
    private PrivateUser privateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxWeightWithCargo() {
        return maxWeightWithCargo;
    }

    public void setMaxWeightWithCargo(Integer maxWeightWithCargo) {
        this.maxWeightWithCargo = maxWeightWithCargo;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public PrivateUser getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(PrivateUser privateUser) {
        this.privateUser = privateUser;
    }

    public Boolean getHasGreenCertificate() {
        return hasGreenCertificate;
    }

    public void setHasGreenCertificate(Boolean hasGreenCertificate) {
        this.hasGreenCertificate = hasGreenCertificate;
    }

    public Object get(String paramName) {

        return switch (paramName) {
            case "color" -> color;
            case "maxWeightWithCargo" -> maxWeightWithCargo;
            case "type" -> type;
            case "manufacturer" -> manufacturer;
            case "height" -> height;
            case "hasGreenCertificate" -> hasGreenCertificate;
            default -> null;
        };
    }
}

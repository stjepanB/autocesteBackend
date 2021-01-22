package diplomski.autoceste.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PrivateUserBill {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String plateMark;
    @Column(nullable = false)
    private LocalDateTime recordedTimeEntry;
    @Column(nullable = false)
    private LocalDateTime recordedTimeExit;
    @Column(nullable = false)
    private String locationEntry;
    @Column(nullable = false)
    private String locationExit;
    @Column(nullable = false)
    private Direction direction;
    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "private_user_id", nullable = false)
    private PrivateUser privateUser;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "private_user_bill_discount",
            joinColumns = {@JoinColumn(name = "bill_id")},
            inverseJoinColumns = {@JoinColumn(name = "private_user_bill_id")}
    )
    Set<Discount> discounts = new HashSet<>();

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPlateMark() {
        return plateMark;
    }

    public void setPlateMark(String plateMark) {
        this.plateMark = plateMark;
    }

    public LocalDateTime getRecordedTimeEntry() {
        return recordedTimeEntry;
    }

    public void setRecordedTimeEntry(LocalDateTime recordedTimeEntry) {
        this.recordedTimeEntry = recordedTimeEntry;
    }

    public LocalDateTime getRecordedTimeExit() {
        return recordedTimeExit;
    }

    public void setRecordedTimeExit(LocalDateTime recordedTimeExit) {
        this.recordedTimeExit = recordedTimeExit;
    }

    public String getLocationEntry() {
        return locationEntry;
    }

    public void setLocationEntry(String locationEntry) {
        this.locationEntry = locationEntry;
    }

    public String getLocationExit() {
        return locationExit;
    }

    public void setLocationExit(String locationExit) {
        this.locationExit = locationExit;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public PrivateUser getPrivateUser() {
        return privateUser;
    }

    public void setPrivateUser(PrivateUser privateUser) {
        this.privateUser = privateUser;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

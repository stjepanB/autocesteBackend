package diplomski.autoceste.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Discount {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @Column(nullable = false)
    private Float percentage;

    @ManyToMany(mappedBy = "discounts")
    private Set<PrivateUserBill> bills = new HashSet<>();

    @ElementCollection
    private Map<String, String> labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PrivateUserBill> getBills() {
        return bills;
    }

    public void setBills(Set<PrivateUserBill> bills) {
        this.bills = bills;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public List<String> getLabels() {
        return new ArrayList<>(labels.keySet());
    }

    public void setLabels(List<String> labels) {
        this.labels = labels.stream().collect(Collectors.toMap(str -> str, str -> str));
    }
}

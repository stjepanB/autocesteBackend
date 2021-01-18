package diplomski.autoceste.forms;

import diplomski.autoceste.models.Discount;

import java.time.LocalDate;
import java.util.List;

public class DiscountDto {

    private String name;
    private List<String> labels;
    private Float percentage;
    private LocalDate startDate;
    private LocalDate endDate;

    public DiscountDto(Discount d) {
        this.name = d.getName();
        this.startDate = d.getStartDate();
        this.endDate = d.getEndDate();
        this.labels = d.getLabels();
        this.percentage = d.getPercentage();
    }

    public DiscountDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
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

    public Discount toDiscount() {
        Discount d = new Discount();
        d.setStartDate(this.startDate);
        d.setEndDate(this.endDate);
        d.setPercentage(this.percentage);
        d.setName(this.name);

        return d;
    }
}

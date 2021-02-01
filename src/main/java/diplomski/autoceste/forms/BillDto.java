package diplomski.autoceste.forms;

import diplomski.autoceste.models.PrivateUserBill;

import java.time.LocalDateTime;

public class BillDto {

    private Long id;
    private String plate;
    private LocalDateTime entryTime;
    private String entry;
    private LocalDateTime exitTime;
    private String exit;
    private Double amount;

    public BillDto(PrivateUserBill p) {
        this.id = p.getId();
        this.plate = p.getPlateMark();
        this.entry = p.getLocationEntry();
        this.entryTime = p.getRecordedTimeEntry();
        this.exit = p.getLocationExit();
        this.exitTime = p.getRecordedTimeExit();
        this.amount = p.getAmount();
    }

    public BillDto() {
    }

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

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

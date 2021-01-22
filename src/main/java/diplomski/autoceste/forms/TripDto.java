package diplomski.autoceste.forms;

import diplomski.autoceste.models.Direction;

import java.util.List;
import java.time.LocalDateTime;

public class TripDto {

    private String plateMark;
    private LocalDateTime recordedTimeEntry;
    private LocalDateTime recordedTimeExit;
    private String locationEntry;
    private String locationExit;
    private List<String> locations;
    private Direction direction;

    public TripDto() {
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}

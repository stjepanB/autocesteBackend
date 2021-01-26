package diplomski.autoceste.services;

import java.time.LocalDateTime;

public interface ReportService {

    boolean addReport(String plate, LocalDateTime entryTime, LocalDateTime exitTime, String entry, String exit);
}

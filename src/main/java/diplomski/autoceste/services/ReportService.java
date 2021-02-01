package diplomski.autoceste.services;

import diplomski.autoceste.models.Report;

import java.time.LocalDateTime;
import java.util.List;
public interface ReportService {

    boolean addReport(String plate, LocalDateTime entryTime, LocalDateTime exitTime, String entry, String exit);

    List<Report> getReports();
}

package diplomski.autoceste.services;

import diplomski.autoceste.models.Report;
import diplomski.autoceste.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository repository;

    @Autowired
    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addReport(String plate, LocalDateTime entryTime, LocalDateTime exitTime, String entry, String exit) {
        Report r = new Report();
        r.setExitTime(exitTime);
        r.setEntryTime(entryTime);
        r.setPlate(plate);
        r.setLocationEntry(entry);
        r.setLocationExit(exit);

        try {
            repository.save(r);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Report> getReports() {
        return repository.findAll();
    }
}

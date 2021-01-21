package diplomski.autoceste.services;

import diplomski.autoceste.models.HighwaySection;
import diplomski.autoceste.repositories.HighwaySectionRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighwaySectionServiceImpl implements HighwaySectionService {

    private HighwaySectionRepository repository;

    @Autowired
    public HighwaySectionServiceImpl(HighwaySectionRepository repository) {
        this.repository = repository;
    }

    public List<HighwaySection> getHighwaySections() {
        return repository.findAll();
    }

    public List<HighwaySection> getHighwaySections(HighwaySection start, HighwaySection end) {
        //TODO
        throw new NotYetImplementedException();
    }

    public HighwaySection addHighwaySection(HighwaySection highwaySection) {
        return repository.save(highwaySection);
    }

    @Override
    public boolean addHighwaySections(List<HighwaySection> sections) {
        try {
            repository.saveAll(sections);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

}

package diplomski.autoceste.services;

import diplomski.autoceste.models.HighwaySection;
import diplomski.autoceste.repositories.HighwaySectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<HighwaySection> getHighwaySections(String start, String end) {

        List<HighwaySection> sections = repository.findAll();

        int indexStart = sections.indexOf(sections.stream().filter(e-> e.getSectionStart().equals(start)).findFirst());
        int indexEnd = sections.indexOf(sections.stream().filter(e-> e.getSectionStart().equals(end)).findFirst());

        List<HighwaySection> ret = new ArrayList<>();

        return null;
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

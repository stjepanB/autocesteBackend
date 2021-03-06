package diplomski.autoceste.services;

import diplomski.autoceste.models.HighwaySection;
import diplomski.autoceste.repositories.HighwaySectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<HighwaySection> getHighwaySections(List<String> locations) {

        List<HighwaySection> sections = repository.findAll();

        return sections.stream().filter(e -> locations.contains(e.getSectionStart())).collect(Collectors.toList());
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

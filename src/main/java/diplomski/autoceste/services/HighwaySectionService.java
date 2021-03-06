package diplomski.autoceste.services;

import diplomski.autoceste.models.HighwaySection;

import java.util.List;

public interface HighwaySectionService {
    List<HighwaySection> getHighwaySections();

    List<HighwaySection> getHighwaySections(List<String> locations);

    HighwaySection addHighwaySection(HighwaySection highwaySection);

    boolean addHighwaySections(List<HighwaySection> sections);
}

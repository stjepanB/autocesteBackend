package diplomski.autoceste.populators;

import diplomski.autoceste.models.HighwaySection;
import diplomski.autoceste.services.HighwaySectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class HighwaySectionPopulate {

    private final HighwaySectionService service;
    private final ResourceLoader resourceLoader;

    @Autowired
    public HighwaySectionPopulate(HighwaySectionService service, ResourceLoader resourceLoader) {
        this.service = service;

        this.resourceLoader = resourceLoader;
    }

    public void populate() throws IOException {


        Resource resource = resourceLoader.getResource("classpath:final.txt");
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }


        String sectionStart = "Lučko";
        for (String s : lines) {
            String[] tmp = s.split(";");
            HighwaySection section = new HighwaySection();
            section.setSectionStart(sectionStart);
            section.setSectionEnd(tmp[0]);
            sectionStart = tmp[0];

            String[] catIA = tmp[1].split(",");
            String[] catI = tmp[2].split(",");
            String[] catII = tmp[3].split(",");
            String[] catIII = tmp[4].split(",");
            String[] catIV = tmp[5].split(",");

            section.setInfrastructureCostIA(Double.parseDouble(catIA[0]));
            section.setOutsideCostIA(Double.parseDouble(catIA[1]));

            section.setInfrastructureCostI(Double.parseDouble(catI[0]));
            section.setOutsideCostI(Double.parseDouble(catI[1]));

            section.setInfrastructureCostII(Double.parseDouble(catII[0]));
            section.setOutsideCostII(Double.parseDouble(catII[1]));

            section.setInfrastructureCostIII(Double.parseDouble(catIII[0]));
            section.setOutsideCostIII(Double.parseDouble(catIII[1]));

            section.setInfrastructureCostIV(Double.parseDouble(catIV[0]));
            section.setOutsideCostIV(Double.parseDouble(catIV[1]));

            service.addHighwaySection(section);
        }
    }
}

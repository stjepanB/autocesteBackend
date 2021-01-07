package diplomski.autoceste.forms.highwaySections;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import diplomski.autoceste.models.HighwaySection;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize
public class HighwaySectionDto {

    @JsonProperty
    private Long key;
    @JsonProperty
    private String section;
    @JsonProperty
    private Map<String, Double> IA;
    @JsonProperty
    private Map<String, Double> I;
    @JsonProperty
    private Map<String, Double> II;
    @JsonProperty
    private Map<String, Double> III;
    @JsonProperty
    private Map<String, Double> IV;

    public HighwaySectionDto(HighwaySection highwaySection) {
        this.key = highwaySection.getId();
        this.section = highwaySection.getSectionStart() + " - " + highwaySection.getSectionEnd();
        this.IA = new HashMap<>();
        this.IA.put("infrastructure", highwaySection.getInfrastructureCostIA());
        this.IA.put("outside", highwaySection.getOutsideCostIA());

        this.I = new HashMap<>();
        this.I.put("infrastructure", highwaySection.getInfrastructureCostI());
        this.I.put("outside", highwaySection.getOutsideCostI());

        this.II = new HashMap<>();
        this.II.put("infrastructure", highwaySection.getInfrastructureCostII());
        this.II.put("outside", highwaySection.getOutsideCostII());

        this.III = new HashMap<>();
        this.III.put("infrastructure", highwaySection.getInfrastructureCostIII());
        this.III.put("outside", highwaySection.getOutsideCostIII());

        this.IV = new HashMap<>();
        this.IV.put("infrastructure", highwaySection.getInfrastructureCostIV());
        this.IV.put("outside", highwaySection.getOutsideCostIV());

    }

}


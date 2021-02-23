package diplomski.autoceste.models;

import javax.annotation.Nonnegative;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HighwaySection {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String sectionStart;
    @Column(nullable = false)
    private String sectionEnd;
    @Column(nullable = false)
    @Nonnegative
    private Double infrastructureCostIA;
    @Column(nullable = false)
    @Nonnegative
    private Double outsideCostIA;
    @Column(nullable = false)
    @Nonnegative
    private Double infrastructureCostI;
    @Column(nullable = false)
    @Nonnegative
    private Double outsideCostI;
    @Column(nullable = false)
    @Nonnegative
    private Double infrastructureCostII;
    @Column(nullable = false)
    @Nonnegative
    private Double outsideCostII;
    @Column(nullable = false)
    @Nonnegative
    private Double infrastructureCostIII;
    @Column(nullable = false)
    @Nonnegative
    private Double outsideCostIII;
    @Column(nullable = false)
    @Nonnegative
    private Double infrastructureCostIV;
    @Column(nullable = false)
    @Nonnegative
    private Double outsideCostIV;
    private Integer distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionStart() {
        return sectionStart;
    }

    public void setSectionStart(String sectionStart) {
        this.sectionStart = sectionStart;
    }

    public String getSectionEnd() {
        return sectionEnd;
    }

    public void setSectionEnd(String sectionEnd) {
        this.sectionEnd = sectionEnd;
    }

    public Double getInfrastructureCostIA() {
        return infrastructureCostIA;
    }

    public void setInfrastructureCostIA(Double infrastructureCostIA) {
        this.infrastructureCostIA = infrastructureCostIA;
    }

    public Double getOutsideCostIA() {
        return outsideCostIA;
    }

    public void setOutsideCostIA(Double outsideCostIA) {
        this.outsideCostIA = outsideCostIA;
    }

    public Double getInfrastructureCostI() {
        return infrastructureCostI;
    }

    public void setInfrastructureCostI(Double infrastructureCostI) {
        this.infrastructureCostI = infrastructureCostI;
    }

    public Double getOutsideCostI() {
        return outsideCostI;
    }

    public void setOutsideCostI(Double outsideCostI) {
        this.outsideCostI = outsideCostI;
    }

    public Double getInfrastructureCostII() {
        return infrastructureCostII;
    }

    public void setInfrastructureCostII(Double infrastructureCostII) {
        this.infrastructureCostII = infrastructureCostII;
    }

    public Double getOutsideCostII() {
        return outsideCostII;
    }

    public void setOutsideCostII(Double outsideCostII) {
        this.outsideCostII = outsideCostII;
    }

    public Double getInfrastructureCostIII() {
        return infrastructureCostIII;
    }

    public void setInfrastructureCostIII(Double infrastructureCostIII) {
        this.infrastructureCostIII = infrastructureCostIII;
    }

    public Double getOutsideCostIII() {
        return outsideCostIII;
    }

    public void setOutsideCostIII(Double outsideCostIII) {
        this.outsideCostIII = outsideCostIII;
    }

    public Double getInfrastructureCostIV() {
        return infrastructureCostIV;
    }

    public void setInfrastructureCostIV(Double infrastructureCostIV) {
        this.infrastructureCostIV = infrastructureCostIV;
    }

    public Double getOutsideCostIV() {
        return outsideCostIV;
    }

    public void setOutsideCostIV(Double outsideCostIV) {
        this.outsideCostIV = outsideCostIV;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}

package sboj.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "job_applications")
public class JobApplication extends BaseEntity {
    private String sector;
    private String profession;
    private BigDecimal salary;
    private String description;
    private boolean isDeleted;

    @Column(name = "sector", nullable = false)
    public String getSector() {
        return sector;
    }

    @Column(name = "profession", nullable = false)
    public String getProfession() {
        return profession;
    }

    @Column(name = "salary", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getSalary() {
        return salary;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    public boolean getDeleted() {
        return isDeleted;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

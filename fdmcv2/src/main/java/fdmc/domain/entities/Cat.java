package fdmc.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "cats")
public class Cat extends BaseEntity {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addedOn;
    private Boolean hasPassport;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "breed", nullable = false)
    public String getBreed() {
        return breed;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    @Column(name = "age", nullable = false)
    public Integer getAge() {
        return age;
    }

    @Column(name = "gender", nullable = false)
    public String getGender() {
        return gender;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    @Column(name = "added_on", nullable = false)
    public Date getAddedOn() {
        return addedOn;
    }

    @Column(name = "has_passport", nullable = false)
    public Boolean getHasPassport() {
        return hasPassport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}

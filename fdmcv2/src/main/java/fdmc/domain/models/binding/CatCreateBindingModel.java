package fdmc.domain.models.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

public class CatCreateBindingModel {
    private String name;
    private String breed;
    private String color;
    private Integer age;
    private String gender;
    private BigDecimal price;
    private Date addedOn;
    private Boolean hasPassport;

    @NotNull
    @Size(min = 2, max = 10)
    public String getName() {
        return name;
    }

    @NotNull
    @Size(min = 5, max = 20)
    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    @NotNull
    @Min(value = 1)
    @Max(value = 31)
    public Integer getAge() {
        return age;
    }

    @NotNull
    public String getGender() {
        return gender;
    }

    @NotNull
    @DecimalMin(value = "0.01")
    public BigDecimal getPrice() {
        return price;
    }

    @NotNull
    public Date getAddedOn() {
        return addedOn;
    }

    @NotNull
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

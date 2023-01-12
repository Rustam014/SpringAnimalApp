package animals.main.API.DTOs.DTO;


import java.time.LocalDateTime;
import java.util.List;

public class FoodDTO {

    private Long id;

    private String type;

    protected LocalDateTime date;

    private Long supply;

    private List<Long> animals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public List<Long> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Long> animals) {
        this.animals = animals;
    }
}

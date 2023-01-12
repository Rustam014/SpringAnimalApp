package animals.main.API.DTOs.DTO;

import animals.main.Data.Entities.Animal;

import java.time.LocalDateTime;

public class ExaminationDTO {

    private Long id;

    protected LocalDateTime date;

    private Long weight;

    private String status;

    private Long animal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        if(animal == null){
            return;
        }
        this.animal = animal.getId();
    }
}

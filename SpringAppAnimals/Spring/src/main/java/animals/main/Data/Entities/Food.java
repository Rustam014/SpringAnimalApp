package animals.main.Data.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    protected LocalDateTime date;

    @Column(nullable = false)
    private Long supply;

    @ManyToMany
    private List<Animal> animals;



    public Food() {
    }

    public Food(String type, LocalDateTime date, Long supply) {
        this.type = type;
        this.date = date;
        this.supply = supply;
    }

    public Food(Long id, String type, LocalDateTime date, Long supply, List<Animal> animals) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.supply = supply;
        this.animals = animals;
    }

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

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public void removeAnimal(Long animalId) {
        if(this.animals.isEmpty()){
            return;
        }
        this.animals.removeIf(animal -> animal.getId().equals(animalId));
    }

    public void addAnimal(Animal _animal) {
        if(!this.animals.contains(_animal)) {
            this.animals.add(_animal);
        }
    }
}

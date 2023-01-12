package animals.main.Data.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Examination {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    protected LocalDateTime date;

    @Column(nullable = false)
    private Long weight;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Animal animal;

    public Examination() {
    }

    public Examination(LocalDateTime date, Long weight, String status) {
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public Examination(Long id, LocalDateTime date, Long weight, String status, Animal animal) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.status = status;
        this.animal = animal;
    }

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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void removeAnimal() {
        this.animal = null;
    }
}

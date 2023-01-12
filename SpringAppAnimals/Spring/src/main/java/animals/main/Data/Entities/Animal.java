package animals.main.Data.Entities;

import animals.main.API.DTOs.DTO.ExaminationDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Animal {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    protected LocalDateTime birthday;

    @ManyToMany
    private List<Food> food;

    @OneToMany
    private List<Examination> exams;

    public Animal(String name, String gender, LocalDateTime birthday) {
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Animal() {
    }

    public Animal(Long id, String name, String gender, LocalDateTime birthday, List<Food> food, List<Examination> exams) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.food = food;
        this.exams = exams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public List<Examination> getExams() {
        return exams;
    }

    public void setExams(List<Examination> exams) {
        this.exams = exams;
    }

    public void addFood(Food _food) {
        if(!this.food.contains(_food)) {
            this.food.add(_food);
        }
    }

    public void addExamination(Examination _exam) {
        if(!this.exams.contains(_exam)) {
            this.exams.add(_exam);
        }
    }

    public void removeFood(Long foodId) {
        if(this.food.isEmpty()){
            return;
        }
        this.food.removeIf(food -> food.getId().equals(foodId));
    }

    public void removeExam(Long examId) {
        if(this.exams.isEmpty()){
            return;
        }
        this.exams.removeIf(exam -> exam.getId().equals(examId));
    }
}

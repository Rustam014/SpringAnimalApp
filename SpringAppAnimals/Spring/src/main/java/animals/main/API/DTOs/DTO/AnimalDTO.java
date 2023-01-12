package animals.main.API.DTOs.DTO;
import java.time.LocalDateTime;
import java.util.List;

public class AnimalDTO {

    private Long id;

    private String name;

    private String gender;

    private LocalDateTime birthday;

    private List<Long> food;

    private List<Long> exams;

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

    public List<Long> getFood() {
        return food;
    }

    public void setFood(List<Long> food) {
        this.food = food;
    }

    public List<Long> getExams() {
        return exams;
    }

    public void setExams(List<Long> exams) {
        this.exams = exams;
    }
}

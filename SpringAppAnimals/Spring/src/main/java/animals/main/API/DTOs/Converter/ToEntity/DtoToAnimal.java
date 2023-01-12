package animals.main.API.DTOs.Converter.ToEntity;

import animals.main.API.DTOs.DTO.AnimalDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import animals.main.Service.ExaminationService;
import animals.main.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class DtoToAnimal implements Function<AnimalDTO, Animal> {

    @Autowired
    private FoodService foodService;

    @Autowired
    private ExaminationService examinationService;

    @Override
    public Animal apply(AnimalDTO animalDTO) {
        List<Food> food = new ArrayList<>();
        List<Examination> exams = new ArrayList<>();
        List<Long> foodIds = animalDTO.getFood();
        List<Long> examsIds = animalDTO.getExams();

        if(foodIds != null && !foodIds.isEmpty()) {
            for (Long foodId : foodIds) {
                food.add(foodService.readById(foodId).get());
            }
        }

        if(examsIds!= null && !examsIds.isEmpty()) {
            for (Long examId : examsIds) {
                exams.add(examinationService.readById(examId).get());
            }
        }

        return new Animal(animalDTO.getId(), animalDTO.getName(),animalDTO.getGender(),animalDTO.getBirthday(),food,exams);
    }
}

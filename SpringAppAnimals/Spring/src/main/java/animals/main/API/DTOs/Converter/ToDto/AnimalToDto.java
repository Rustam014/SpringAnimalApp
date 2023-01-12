package animals.main.API.DTOs.Converter.ToDto;

import animals.main.API.DTOs.DTO.AnimalDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class AnimalToDto implements Function<Animal, AnimalDTO> {
    @Override
    public AnimalDTO apply(Animal animal) {
        var res = new AnimalDTO();
        res.setId(animal.getId());
        res.setName(animal.getName());
        res.setGender(animal.getGender());
        res.setBirthday(animal.getBirthday());
        List<Long> foodIds = new ArrayList<>();
        if(animal.getFood().isEmpty() || animal.getFood() == null){
            res.setFood(foodIds);
        }
        else{
            for(Food food: animal.getFood()){
                foodIds.add(food.getId());
            }
            res.setFood(foodIds);
        }
        List<Long> examIds = new ArrayList<>();
        if(animal.getExams().isEmpty() || animal.getExams() == null){
            res.setExams(examIds);
        }
        else{
            for(Examination examination: animal.getExams()){
                examIds.add(examination.getId());
            }
            res.setExams(examIds);
        }
        return res;
    }
}

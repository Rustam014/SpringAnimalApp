package animals.main.API.DTOs.Converter.ToEntity;

import animals.main.API.DTOs.DTO.FoodDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Food;
import animals.main.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class DtoToFood implements Function<FoodDTO, Food> {

    @Autowired
    private AnimalService animalService;
    @Override
    public Food apply(FoodDTO foodDTO) {
        List<Animal> animals = new ArrayList<>();
        List<Long> animalsIds = foodDTO.getAnimals();

        if(animalsIds!=null && !animalsIds.isEmpty()) {
            for (Long animalId : animalsIds) {
                animals.add(animalService.readById(animalId).get());
            }
        }

        return new Food(foodDTO.getId(),foodDTO.getType(),foodDTO.getDate(),foodDTO.getSupply(),animals);
    }
}

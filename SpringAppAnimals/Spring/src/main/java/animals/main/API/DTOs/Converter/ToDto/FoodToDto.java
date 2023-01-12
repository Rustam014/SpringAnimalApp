package animals.main.API.DTOs.Converter.ToDto;

import animals.main.API.DTOs.DTO.FoodDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Food;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class FoodToDto implements Function<Food, FoodDTO> {
    @Override
    public FoodDTO apply(Food food) {
        var res = new FoodDTO();
        res.setId(food.getId());
        res.setType(food.getType());
        res.setDate(food.getDate());
        res.setSupply(food.getSupply());
        List<Long> animalIds = new ArrayList<>();
        if(food.getAnimals().isEmpty() || food.getAnimals() == null){
            res.setAnimals(animalIds);
        }
        else{
            for(Animal animal: food.getAnimals()){
                animalIds.add(animal.getId());
            }
            res.setAnimals(animalIds);
        }
        return res;
    }
}

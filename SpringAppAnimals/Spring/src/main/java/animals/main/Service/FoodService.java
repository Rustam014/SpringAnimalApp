package animals.main.Service;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Food;
import animals.main.Data.Repository.AnimalRepository;
import animals.main.Data.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FoodRepository foodRepository;

    public FoodService(){}

    public Food create(Food food){
        return foodRepository.save(food);
    }

    public Collection<Food> readAll() {
        return foodRepository.findAll();
    }

    public Optional<Food> readById(Long id) {
        return foodRepository.findById(id);
    }

    public Collection<Food> readAllByType(String name){
        return foodRepository.findAllByType(name);
    }

    public void update(Food food) {
        if(foodRepository.existsById(food.getId())) {
            foodRepository.save(food);
            return;
        }
        throw new RuntimeException("Food doesn't exist");
    }

    public void deleteById(Long id) {
        if(foodRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Food doesn't exists");
        }
        Food food = foodRepository.findById(id).get();
        if(food.getAnimals() == null){
            foodRepository.deleteById(id);
            return;
        }

        for(Animal animal: food.getAnimals()){
            animal.removeFood(id);
            animalRepository.save(animal);
        }

        food.setAnimals(new ArrayList<>());
        foodRepository.save(food);
        foodRepository.deleteById(id);
    }
}

package animals.main.Service;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import animals.main.Data.Repository.AnimalRepository;
import animals.main.Data.Repository.ExaminationRepository;
import animals.main.Data.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ExaminationRepository examinationRepository;

    public AnimalService(){}

    public Animal create(Animal animal){
        return animalRepository.save(animal);
    }

    public Collection<Animal> readAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> readById(Long id) {
        return animalRepository.findById(id);
    }

    public void update(Animal animal) {
        if(animalRepository.existsById(animal.getId())) {
            animalRepository.save(animal);
            return;
        }
        throw new RuntimeException("Animal doesn't exist");
    }

    public void deleteById(Long id) {
        if(animalRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Animal doesn't exists");
        }
        Animal animal = animalRepository.findById(id).get();
        if(animal.getFood() == null && animal.getExams() == null){
            animalRepository.deleteById(id);
            return;
        }
        for(Food food: animal.getFood()){
            food.removeAnimal(id);
            foodRepository.save(food);
        }

        for(Examination examination: animal.getExams()){
            examination.removeAnimal();
            examinationRepository.save(examination);
        }
        animal.setFood(new ArrayList<>());
        animal.setExams(new ArrayList<>());
        animalRepository.save(animal);
        animalRepository.deleteById(id);
    }
}

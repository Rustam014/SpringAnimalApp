package animals.main.API.Controllers;

import animals.main.API.DTOs.Converter.ToDto.ExaminationToDto;
import animals.main.API.DTOs.Converter.ToDto.FoodToDto;
import animals.main.API.DTOs.Converter.ToEntity.DtoToExamination;
import animals.main.API.DTOs.Converter.ToEntity.DtoToFood;
import animals.main.API.DTOs.DTO.ExaminationDTO;
import animals.main.API.DTOs.DTO.FoodDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import animals.main.Service.AnimalService;
import animals.main.Service.ExaminationService;
import animals.main.Service.FoodService;
import org.apache.catalina.connector.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    private final AnimalService animalService;
    private final FoodToDto foodToDto;
    private final DtoToFood dtoToFood;

    public FoodController(FoodService foodService,AnimalService animalService,FoodToDto foodToDto, DtoToFood dtoToFood) {
        this.foodService = foodService;
        this.animalService = animalService;
        this.foodToDto = foodToDto;
        this.dtoToFood = dtoToFood;
    }
    @GetMapping
    public Object getFood(@RequestParam(required = false) Long id) {
        if(id==null){
            try{
                Collection<FoodDTO> foodDTOS = foodService.readAll().stream().map(foodToDto).toList();
                return new ResponseEntity<>(foodDTOS, HttpStatus.OK);
            }
            catch (HttpClientErrorException.BadRequest errorException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        else {
            Optional<Food> food = foodService.readById(id);
            if(food.isPresent()){
                return new ResponseEntity<>(foodToDto.apply(food.get()), HttpStatus.OK);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/bad_food")
    public Object getFood(@RequestParam String type) {
        try{
            Collection<FoodDTO> foodDTOS = foodService.readAllByType(type).stream().map(foodToDto).toList();
            return new ResponseEntity<>(foodDTOS, HttpStatus.OK);
        }
        catch (HttpClientErrorException.BadRequest errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object create(@RequestBody FoodDTO e) {
        try{
            FoodDTO foodDTO = foodToDto.apply(foodService.create((dtoToFood.apply(e))));
            return new ResponseEntity<>(foodDTO, HttpStatus.CREATED);
        }
        catch (HttpClientErrorException.BadRequest | HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public void update(@RequestBody FoodDTO e, @RequestParam("id") Long id) {
        try{
            e.setId(id);
            foodService.update(dtoToFood.apply(e));
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/feed")
    public Object feedAnimal(@RequestParam("foodId") Long foodId, @RequestParam("animalId") Long animalId) {
        Optional<Food> food = foodService.readById(foodId);
        Optional<Animal> animal = animalService.readById(animalId);
        if(food.isPresent() && animal.isPresent()){
            food.get().addAnimal(animal.get());
            animal.get().addFood(food.get());
            foodService.update(food.get());
            animalService.update(animal.get());
            throw new ResponseStatusException(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        try{
            foodService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpClientErrorException.NotFound errorException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

package animals.main.API.Controllers;

import animals.main.API.DTOs.Converter.ToDto.AnimalToDto;
import animals.main.API.DTOs.Converter.ToEntity.DtoToAnimal;
import animals.main.API.DTOs.DTO.AnimalDTO;
import animals.main.API.DTOs.DTO.ExaminationDTO;
import animals.main.API.DTOs.DTO.FoodDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Service.AnimalService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;
    private final AnimalToDto animalToDto;
    private final DtoToAnimal dtoToAnimal;

    public AnimalController(AnimalService animalService,AnimalToDto animalToDto, DtoToAnimal dtoToAnimal) {
        this.animalService = animalService;
        this.animalToDto = animalToDto;
        this.dtoToAnimal = dtoToAnimal;
    }

    @GetMapping
    public Object getAnimal(@RequestParam(required = false) Long id) {
        if(id == null){
            try{
                Collection<AnimalDTO> animalDTOS = animalService.readAll().stream().map(animalToDto).toList();
                return new ResponseEntity<>(animalDTOS, HttpStatus.OK);
            }
            catch (HttpClientErrorException.BadRequest errorException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            Optional<Animal> animal = animalService.readById(id);
            if(animal.isPresent()){
                return new ResponseEntity<>(animalToDto.apply(animal.get()), HttpStatus.OK);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Object create(@RequestBody AnimalDTO e) {
        try{
            AnimalDTO animalDTO = animalToDto.apply(animalService.create((dtoToAnimal.apply(e))));
            return new ResponseEntity<>(animalDTO, HttpStatus.CREATED);
        }
        catch (HttpClientErrorException.BadRequest | HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public void update(@RequestBody AnimalDTO e, @RequestParam("id") Long id) {
        try{
            e.setId(id);
            animalService.update(dtoToAnimal.apply(e));
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        try{
            animalService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpClientErrorException.NotFound errorException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

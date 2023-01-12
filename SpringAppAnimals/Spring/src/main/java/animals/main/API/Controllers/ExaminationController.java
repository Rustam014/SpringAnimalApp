package animals.main.API.Controllers;

import animals.main.API.DTOs.Converter.ToDto.AnimalToDto;
import animals.main.API.DTOs.Converter.ToDto.ExaminationToDto;
import animals.main.API.DTOs.Converter.ToEntity.DtoToAnimal;
import animals.main.API.DTOs.Converter.ToEntity.DtoToExamination;
import animals.main.API.DTOs.DTO.AnimalDTO;
import animals.main.API.DTOs.DTO.ExaminationDTO;
import animals.main.API.DTOs.DTO.FoodDTO;
import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import animals.main.Service.AnimalService;
import animals.main.Service.ExaminationService;
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
@RequestMapping("/examination")
public class ExaminationController {
    private final ExaminationService examinationService;
    private final AnimalService animalService;
    private final ExaminationToDto examinationToDto;
    private final DtoToExamination dtoToExamination;

    public ExaminationController(ExaminationService examinationService,AnimalService animalService,ExaminationToDto examinationToDto, DtoToExamination dtoToExamination) {
        this.examinationService = examinationService;
        this.animalService = animalService;
        this.examinationToDto = examinationToDto;
        this.dtoToExamination = dtoToExamination;
    }



    @GetMapping
    public Object getExam(@RequestParam(required = false) Long id) {
        if(id == null){
            try{
                Collection<ExaminationDTO> examinationDTOS = examinationService.readAll().stream().map(examinationToDto).toList();
                return new ResponseEntity<>(examinationDTOS, HttpStatus.OK);
            }
            catch (HttpClientErrorException.BadRequest errorException){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        else{
            Optional<Examination> examination = examinationService.readById(id);
            if(examination.isPresent()){
                return new ResponseEntity<>(examinationToDto.apply(examination.get()), HttpStatus.OK);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Object create(@RequestBody ExaminationDTO e) {
        try{
            ExaminationDTO examinationDTO = examinationToDto.apply(examinationService.create((dtoToExamination.apply(e))));
            return new ResponseEntity<>(examinationDTO, HttpStatus.CREATED);
        }
        catch (HttpClientErrorException.BadRequest | HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public void update(@RequestBody ExaminationDTO e, @RequestParam("id") Long id) {
        try{
            e.setId(id);
            examinationService.update(dtoToExamination.apply(e));
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpServerErrorException.InternalServerError errorException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/set_animal")
    public Object addExam(@RequestParam("examId") Long examId, @RequestParam("animalId") Long animalId) {
        Optional<Examination> examination = examinationService.readById(examId);
        Optional<Animal> animal = animalService.readById(animalId);
        if(examination.isPresent() && animal.isPresent()){
            examination.get().setAnimal(animal.get());
            animal.get().addExamination(examination.get());
            examinationService.update(examination.get());
            animalService.update(animal.get());
            throw new ResponseStatusException(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") Long id) {
        try{
            examinationService.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK);
        }
        catch (HttpClientErrorException.NotFound errorException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

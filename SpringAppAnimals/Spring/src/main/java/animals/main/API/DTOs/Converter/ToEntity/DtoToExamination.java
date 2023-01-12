package animals.main.API.DTOs.Converter.ToEntity;

import animals.main.API.DTOs.DTO.ExaminationDTO;
import animals.main.Data.Entities.Examination;
import animals.main.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DtoToExamination  implements Function<ExaminationDTO, Examination> {
    @Autowired
    private AnimalService animalService;

    @Override
    public Examination apply(ExaminationDTO examinationDTO) {
        if(examinationDTO.getAnimal() != null){
            return new Examination(examinationDTO.getId(), examinationDTO.getDate(), examinationDTO.getWeight(), examinationDTO.getStatus(),animalService.readById(examinationDTO.getAnimal()).get());
        }
        return new Examination(examinationDTO.getId(), examinationDTO.getDate(), examinationDTO.getWeight(), examinationDTO.getStatus(),null);
    }
}

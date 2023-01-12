package animals.main.API.DTOs.Converter.ToDto;

import animals.main.API.DTOs.DTO.ExaminationDTO;
import animals.main.Data.Entities.Examination;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExaminationToDto implements Function<Examination, ExaminationDTO> {
    @Override
    public ExaminationDTO apply(Examination examination) {
        var res = new ExaminationDTO();
        res.setId(examination.getId());
        res.setDate(examination.getDate());
        res.setWeight(examination.getWeight());
        res.setStatus(examination.getStatus());
        res.setAnimal(examination.getAnimal());
        return res;
    }
}

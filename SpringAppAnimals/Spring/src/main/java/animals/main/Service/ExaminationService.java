package animals.main.Service;

import animals.main.Data.Entities.Examination;
import animals.main.Data.Repository.AnimalRepository;
import animals.main.Data.Repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public ExaminationService(){}

    public Examination create(Examination examination){
        return examinationRepository.save(examination);
    }

    public Collection<Examination> readAll() {
        return examinationRepository.findAll();
    }

    public Optional<Examination> readById(Long id) {
        return examinationRepository.findById(id);
    }

    public void update(Examination examination) {
        if(examinationRepository.existsById(examination.getId())) {
            examinationRepository.save(examination);
            return;
        }
        throw new RuntimeException("Examination doesn't exist");
    }

    public void deleteById(Long id) {
        if(examinationRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Examination doesn't exists");
        }
        Examination examination = examinationRepository.findById(id).get();
        if(examination.getAnimal() == null){
            examinationRepository.deleteById(id);
            return;
        }
        examination.getAnimal().removeExam(examination.getId());
        animalRepository.save(examination.getAnimal());

        examination.setAnimal(null);
        examinationRepository.save(examination);
        examinationRepository.deleteById(id);
    }
}

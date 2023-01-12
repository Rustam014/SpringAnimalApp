package animals.main.Data.Repository;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {
}

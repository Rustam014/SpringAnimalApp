package animals.main.Data.Repository;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Collection<Food> findAllByType(String name);
}

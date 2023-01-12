package animals.main;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Entities.Food;
import animals.main.Data.Repository.AnimalRepository;
import animals.main.Data.Repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FoodRepository foodRepository;
    @MockBean
    AnimalRepository animalRepository;

    @Test
    void getAllFoodTest() throws Exception {
        Food food1 = new Food(1L, "Meat", LocalDateTime.of(2000,1,1,12,0,0),15L, new ArrayList<>());
        Food food2 = new Food(2L, "Meat", LocalDateTime.of(2000,1,1,12,0,0),150L, new ArrayList<>());
        List<Food> res = new ArrayList<>();
        res.add(food1);
        res.add(food2);
        BDDMockito.given(foodRepository.findAll()).willReturn(res);
        mockMvc.perform(MockMvcRequestBuilders.get("/food"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void getFoodTest() throws Exception {
        Food food1 = new Food(1L, "Meat", LocalDateTime.of(2000,1,1,12,0,0),15L, new ArrayList<>());
        BDDMockito.given(foodRepository.findById(1L)).willReturn(Optional.of(food1));
        mockMvc.perform(MockMvcRequestBuilders.get("/food?id={id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void feedTest() throws Exception{
        Food food1 = new Food(1L, "Meat", LocalDateTime.of(2000,1,1,12,0,0),15L, new ArrayList<>());
        Animal anim1 = new Animal(1L,"Jonny","Female", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        BDDMockito.given(animalRepository.findById(1L)).willReturn(Optional.of(anim1));
        BDDMockito.given(foodRepository.findById(1L)).willReturn(Optional.of(food1));
        BDDMockito.given(foodRepository.existsById(1L)).willReturn(true);
        BDDMockito.given(animalRepository.existsById(1L)).willReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/food/feed?foodId={foodId}&animalId={animalId}",1,1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}

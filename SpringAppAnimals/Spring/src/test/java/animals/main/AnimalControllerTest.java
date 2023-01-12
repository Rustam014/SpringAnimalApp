package animals.main;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AnimalRepository animalRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAnimalsTest() throws Exception {
        Animal anim1 = new Animal(1L,"Jonny","Female", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        Animal anim2 = new Animal(2L,"Josh","Male", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        List<Animal> res = new ArrayList<>();
        res.add(anim1);
        res.add(anim2);
        BDDMockito.given(animalRepository.findAll()).willReturn(res);
        mockMvc.perform(MockMvcRequestBuilders.get("/animal"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void getAnimalTest() throws Exception {
        Animal anim1 = new Animal(1L,"Jonny","Female", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        BDDMockito.given(animalRepository.findById(1L)).willReturn(Optional.of(anim1));
        mockMvc.perform(MockMvcRequestBuilders.get("/animal?id={id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }


    @Test
    void postAnimalTest() throws Exception {
        Animal anim1 = new Animal(1L,"Jones","Female", LocalDateTime.of(2016,1,25,19,30,0),new ArrayList<>(), new ArrayList<>());
        BDDMockito.given(animalRepository.save(any(Animal.class))).willReturn(anim1);
        mockMvc.perform(MockMvcRequestBuilders.post("/animal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Jones\", \"gender\": \"Female\", \"birthday\": \"2016-01-25T19:30:00\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(print());
    }

    @Test
    void putAnimalTest() throws Exception {
        BDDMockito.given(animalRepository.existsById(1555L)).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.put("/animal?id={id}",1555L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Jones\", \"gender\": \"Male\", \"birthday\": \"2016-01-25T19:30:00\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(print());
    }

    @Test
    void deleteAnimalTest() throws Exception {
        Animal animal = new Animal(1555L,"Jonny","Female", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        BDDMockito.given(animalRepository.findById(1555L)).willReturn(Optional.of(animal));

        mockMvc.perform(MockMvcRequestBuilders.delete("/animal?id={id}",1555L))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andDo(print());
    }
}

package animals.main;

import animals.main.Data.Entities.Animal;
import animals.main.Data.Entities.Examination;
import animals.main.Data.Repository.AnimalRepository;
import animals.main.Data.Repository.ExaminationRepository;
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
public class ExaminationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExaminationRepository examinationRepository;

    @MockBean
    AnimalRepository animalRepository;

    @Test
    void getExaminationsTest() throws Exception {
        Examination exam1 = new Examination(1L,LocalDateTime.of(2000,1,1,12,0,0),15L,"Good", new Animal());
        Examination exam2 = new Examination(2L,LocalDateTime.of(1995,12,12,14,30,30),150L,"Bad", new Animal());
        List<Examination> res = new ArrayList<>();
        res.add(exam1);
        res.add(exam2);
        BDDMockito.given(examinationRepository.findAll()).willReturn(res);
        mockMvc.perform(MockMvcRequestBuilders.get("/examination"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void getExaminationTest() throws Exception {
        Examination exam1 = new Examination(1L,LocalDateTime.of(2000,1,1,12,0,0),15L,"Good",new Animal());
        BDDMockito.given(examinationRepository.findById(1L)).willReturn(Optional.of(exam1));
        mockMvc.perform(MockMvcRequestBuilders.get("/examination?id={id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    void feedTest() throws Exception{
        Examination exam1 = new Examination(1L,LocalDateTime.of(2000,1,1,12,0,0),15L,"Good",new Animal());
        Animal anim1 = new Animal(1L,"Jonny","Female", LocalDateTime.MIN,new ArrayList<>(),new ArrayList<>());
        BDDMockito.given(animalRepository.findById(1L)).willReturn(Optional.of(anim1));
        BDDMockito.given(examinationRepository.findById(1L)).willReturn(Optional.of(exam1));
        BDDMockito.given(examinationRepository.existsById(1L)).willReturn(true);
        BDDMockito.given(animalRepository.existsById(1L)).willReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/examination/set_animal?examId={examId}&animalId={animalId}",1,1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }
}

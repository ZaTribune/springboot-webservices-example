package zatribune.spring.example.webservices.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zatribune.spring.example.webservices.data.dto.CategoryDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {


    @Mock
    CategoryServiceImpl categoryService;
    MockMvc mockMvc;
    CategoryDTO c1, c2, c3, c4, c5;

    @BeforeEach
    void setUp() {
        c1 = new CategoryDTO();
        c1.setName("Fruits");
        c2 = new CategoryDTO();
        c2.setName("Family");
        c3 = new CategoryDTO();
        c3.setName("Drinks");
        c4 = new CategoryDTO();
        c4.setName("Free");
        c5 = new CategoryDTO();
        c5.setName("Fascinating");

    }


    @Test
    void getCategoriesByName() {
        List<CategoryDTO> list=List.of(c1, c2, c4);

        when(categoryService.getCategoriesByName("F", 3)).thenReturn(list);

        List<CategoryDTO> categoryList = categoryService.getCategoriesByName("F", 3);
        assertNotNull(categoryList);
        assertEquals(2, categoryList.size());
    }

    @Test
    void getCategoriesByNameRest() throws Exception {
        List<CategoryDTO> list = List.of(c1, c2, c4, c5);
        String name = "";
        Integer limit = 4;

        when(categoryService.getCategoriesByName(name, limit)).thenReturn(list);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryService).build();
        String response = mockMvc.perform(get("/v1/categories/list")
                .param("name", name)
                .param("limit", limit.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<CategoryDTO> result = mapper.readValue(response, new TypeReference<>() {
        });
        assertEquals(4, result.size());
    }
    @Test
    void createCategory(){

    }
    @Test
    void updateCategory(){

    }
    @Test
    void patchCategory(){

    }
}
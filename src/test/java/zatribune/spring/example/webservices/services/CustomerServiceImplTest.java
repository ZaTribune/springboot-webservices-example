package zatribune.spring.example.webservices.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zatribune.spring.example.webservices.controllers.CustomerServiceImpl;
import zatribune.spring.example.webservices.data.entities.customers.Name;
import zatribune.spring.example.webservices.data.dto.CustomerDTO;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerServiceImpl customerService;

    MockMvc mockMvc;
    CustomerDTO requestCustomer;
    CustomerDTO responseCustomer;
    Name name;
    Long id=10L;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerService).build();
        name = new Name();
        name.setFirst("Martin");
        requestCustomer = new CustomerDTO();
        requestCustomer.setName(name);
        requestCustomer.setEmail("emailgfggf@yahoo.com");
        // what should return
        responseCustomer = new CustomerDTO();
        responseCustomer.setId(id);
        responseCustomer.setName(name);
        responseCustomer.setEmail("emailgfggf@yahoo.com");
        responseCustomer.setUrl("/v1/customers/" + id);
    }

    @Test
    void getById() throws Exception {
        when(customerService.getById(id))
                .thenReturn(responseCustomer);

        String result = mockMvc.perform(get("/v1/customers/"+id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //selection using XPATH selecting xml/json nodes
                .andExpect(jsonPath("$.name.first", equalTo("Martin")))
                .andExpect(jsonPath("$.url", equalTo("/v1/customers/"+id)))
                .andReturn().getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO resultCustomer = objectMapper.readValue(result, CustomerDTO.class);
        assertNotNull(resultCustomer);
        assertEquals(responseCustomer.getUrl(), resultCustomer.getUrl());
        assertEquals(responseCustomer.getId(), resultCustomer.getId());
    }

    @Test
    void getCustomersByName() {
    }

    @Test
    void postCustomer() throws Exception {

        when(customerService.createCustomer(any(CustomerDTO.class)))
                .thenReturn(responseCustomer);

        String customerString = new ObjectMapper().writeValueAsString(requestCustomer);

        //don't use contentType
        String result = mockMvc.perform(post("/v1/customers/").content(customerString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //selection using XPATH selecting xml/json nodes
                .andExpect(jsonPath("$.name.first", equalTo("Martin")))
                .andExpect(jsonPath("$.url", equalTo("/v1/customers/"+id)))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CustomerDTO resultCustomer = objectMapper.readValue(result, CustomerDTO.class);
        assertNotNull(resultCustomer);
        assertEquals(responseCustomer.getUrl(), resultCustomer.getUrl());
        assertEquals(responseCustomer.getId(), resultCustomer.getId());

    }

    @Test
    void putCustomer() throws Exception {
        requestCustomer.setId(id);

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class)))
                .thenReturn(responseCustomer);

        String customerString = new ObjectMapper().writeValueAsString(requestCustomer);

        //don't use contentType
        String result = mockMvc.perform(put("/v1/customers/")
                .param("id", id.toString())
                .content(customerString)
                .contentType(MediaType.APPLICATION_JSON))//perform() end
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //selection using XPATH selecting xml/json nodes
                .andExpect(jsonPath("$.name.first", equalTo("Martin")))
                .andExpect(jsonPath("$.url", equalTo("/v1/customers/"+id)))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        CustomerDTO resultCustomer = objectMapper.readValue(result, CustomerDTO.class);
        assertNotNull(resultCustomer);
        assertEquals(responseCustomer.getId(), resultCustomer.getId());

    }

    @Test
    void patchCustomer() throws Exception {

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class)))
                .thenReturn(responseCustomer);

        String customerString = new ObjectMapper().writeValueAsString(requestCustomer);

        //don't use contentType
        String result = mockMvc.perform(patch("/v1/customers/")
                .param("id", id.toString())
                .content(customerString)
                .contentType(MediaType.APPLICATION_JSON))//perform() end
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //selection using XPATH selecting xml/json nodes
                .andExpect(jsonPath("$.name.first", equalTo("Martin")))
                .andExpect(jsonPath("$.url", equalTo("/v1/customers/"+id)))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        CustomerDTO resultCustomer = objectMapper.readValue(result, CustomerDTO.class);
        assertNotNull(resultCustomer);
        assertEquals(responseCustomer.getId(), resultCustomer.getId());

    }

}
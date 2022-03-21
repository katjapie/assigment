package com.example.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void fetchCountriesTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/countries"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void fetchCountryTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/country/peru"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}

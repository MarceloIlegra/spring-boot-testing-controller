package br.com.marcelo.springboot.controller;


import br.com.marcelo.springboot.App;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
@WebAppConfiguration
public class HelloControllerTest {

    @Autowired private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void helloSuccessTest() throws Exception{
        this.mockMvc.perform(get("/hello").param("name", "Test"))
                .andDo(print())
                .andExpect(content().string("hello Test!"));
    }

    @Test
    public void helloWithUrlTemplateTest() throws Exception{
        this.mockMvc.perform(get("/hello?name=", "Test")).andExpect(status().isOk());
    }

    @Test
    public void helloWithoutParamTest() throws Exception{
        this.mockMvc.perform(get("/hello")).andExpect(status().isBadRequest());
    }

    @Test
    public void routeGettingBadRequest() throws Exception{
        this.mockMvc.perform(get("/badroute")).andExpect(status().isBadRequest());
    }

    @Test
    public void searchSuccessTest() throws Exception{
        this.mockMvc.perform(get("/search").param("id", "1")).andExpect(status().isOk());
    }

    @Test
    public void searchReturnCorrectNameSuccessTest() throws Exception{
        this.mockMvc.perform(get("/search").param("id", "1")).andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    public void searchByIdThatNotExistsSuccessTest() throws Exception{
        this.mockMvc.perform(get("/search").param("id", "5")).andExpect(status().isBadRequest());
    }

    @Test
    public void searchWithMultipleExpectSuccessTest() throws Exception{
        this.mockMvc.perform(get("/search").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    public void getLanguageSuccessTest() throws Exception{
        this.mockMvc.perform(post("/language").param("id", "4").param("name", "Java 8"))
                .andExpect(content().string("{\"id\":4,\"name\":\"Java 8\"}"));
    }

    @Test
    public void getLanguageOverLimitTest() throws Exception{
        this.mockMvc.perform(post("/language").param("id", "4").param("name", "12345678910"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getLanguageEmptyAllFieldTest() throws Exception{
        this.mockMvc.perform(post("/language").param("id", "").param("name", ""))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateAgeSuccessTest() throws Exception{
        this.mockMvc.perform(post("/calculateAge").param("birthdate", "01/03/1990"))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateAgeInvalidFormatDateTest() throws Exception{
        this.mockMvc.perform(post("/calculateAge").param("birthdate", "teste"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}

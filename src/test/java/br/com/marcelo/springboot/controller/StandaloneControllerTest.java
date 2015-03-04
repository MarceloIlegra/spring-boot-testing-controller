
package br.com.marcelo.springboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class StandaloneControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new StandaloneController()).build();
    }

    @Test
    public void showOkSuccess() throws Exception{
        this.mockMvc.perform(get("/standalone")).andExpect(status().isOk());
    }

}

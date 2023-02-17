package com.loja.arenajogos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.service.TelefoneService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers = {TelefoneController.class})
public class TelefoneControllerTeste {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TelefoneService service;

    private ObjectMapper objectMapper;

    @BeforeAll
    public void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testePegaTodosTelefone() throws Exception {
        Mockito.when(service.buscaTodosTelefone())
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/telefone"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
    }
    @Test
    public void testeSalvaTelefone() throws Exception {
        Telefone telefone = new Telefone();
        telefone.setDd("88");
        telefone.setNumero("998759855");

        Mockito.when(service.salvaTelefone(Mockito.mock(Telefone.class)))
                .thenReturn(telefone);

        mockMvc.perform(MockMvcRequestBuilders.post("/telefone")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(telefone)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse();
    }
}

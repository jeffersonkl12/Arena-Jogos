package com.loja.arenajogos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.arenajogos.model.Jogo;
import com.loja.arenajogos.service.JogoService;
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

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(value = {JogoController.class})
public class JogoControllerTeste {

    @MockBean
    private JogoService jogoService;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeAll
    private void init(){
        objectMapper = new ObjectMapper();
    }
    @Test
    public void salvaJogoCamposInvalidosTest() throws Exception {
        Jogo jogo = new Jogo(null,"stardew valley", null,-5,-100.0,null,null);
        Mockito.when(jogoService.salvaJogo(Mockito.any())).thenReturn(jogo);
        mockMvc.perform(MockMvcRequestBuilders.post("/jogo")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jogo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn().getResponse();
    }
}

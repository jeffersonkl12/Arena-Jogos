package com.loja.arenajogos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.arenajogos.model.Produtora;
import com.loja.arenajogos.service.ProdutoraService;
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
@WebMvcTest(controllers = {ProdutoraController.class})
public class ProdutoraControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProdutoraService service;
    private List<Produtora> lista;
    @BeforeAll
    private void init(){
        Produtora p1 = new Produtora();
        Produtora p2 = new Produtora();
        Produtora p3 = new Produtora();
        lista = List.of(p1,p2,p3);
    }
    @Test
    public void teste() throws Exception {
        //when
        Mockito.when(service.buscaTodos())
                        .thenReturn(lista);
        Produtora nova = new Produtora();
        nova.setNome("cdprojects");
        ObjectMapper objectMapper = new ObjectMapper();

        //to
        mockMvc.perform(MockMvcRequestBuilders.post("/produtora")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nova)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse();

    }
}

package com.loja.arenajogos.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loja.arenajogos.dto.ClienteDTO;
import com.loja.arenajogos.model.Cliente;
import com.loja.arenajogos.model.Endereco;
import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.service.ClienteService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(controllers = {ClienteController.class})
public class ClienteControllerTest {
    @MockBean
    private ClienteService service;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @BeforeAll
    public void config(){
        objectMapper = new ObjectMapper();
    }

    @Test
    public void atualizaParcialClienteTest() throws NoSuchFieldException, IllegalAccessException {
        /*
        Endereco endereco = new Endereco(null,"ceara","limoeiro","centro","sobrino","2406","proximo ao banco",null);
        Telefone telefone = new Telefone(null,"88","999759855",null);
        Cliente salvo = new Cliente(null,"douglas",LocalDate.now(), Cliente.SEXO.MASCULINO,"45875478945",endereco,telefone);
        Map<String,Object> camposCliente = new HashMap<>();
        camposCliente.put("cpf","07251370379");
        Mockito.when(service.buscaCliente(Mockito.anyLong())).thenReturn(salvo);
        //when
        System.out.println(salvo);
        //assert
        Assertions.assertDoesNotThrow(()-> clienteController.atualizaParcialCliente(camposCliente,Mockito.anyLong()));

        System.out.println(salvo);
    */
    }

    @Test
    public void salvaClienteTest() throws Exception {
        //give
        Endereco endereco = new Endereco(null,"ceara","limoeiro","centro","sobrino","2406","proximo ao banco",null);
        Telefone telefone = new Telefone(null,"88","999759855",null);
        Cliente salvo = new Cliente(null,"douglas",null, Cliente.SEXO.MASCULINO,"45875478945",endereco,telefone);
        //when
        ClienteDTO clienteDTO = new ModelMapper().map(salvo, ClienteDTO.class);
        Mockito.when(modelMapper.map(Mockito.any(), Mockito.eq(ClienteDTO.class)))
                .thenReturn(clienteDTO);
        Mockito.when(service.salvaCliente(Mockito.any())).thenReturn(salvo);
        //assert
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse();
    }

}

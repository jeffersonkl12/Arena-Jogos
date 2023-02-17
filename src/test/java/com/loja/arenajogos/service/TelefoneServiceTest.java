package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.repository.TelefoneRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TelefoneServiceTest {

    private TelefoneRepository repository;
    private TelefoneService telefoneService;
    @BeforeAll
    public void initTeste(){
        repository = Mockito.mock(TelefoneRepository.class);
        telefoneService = new TelefoneService(repository);
    }
    @ParameterizedTest
    @CsvSource(value = {"88,999759855"})
    @DisplayName(value = "teste salvando novo numero")
    public void salvaTelefoneNovo(String dd,String numero){
        Telefone telefone = new Telefone(null,dd,numero,null);
        Mockito.when(repository.save(Mockito.any())).thenReturn(telefone);
        Assertions.assertEquals(telefone,telefoneService.salvaTelefone(telefone));
        Mockito.verify(repository).save(telefone);

    }

    @Test
    @DisplayName(value = "teste excluindo numero existente")
    public void deletaTelefoneExistente(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->telefoneService.deletaTelefone(Mockito.anyLong()));
        Mockito.verify(repository).existsById(Mockito.anyLong());
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }

    @Test
    @DisplayName(value = "teste excluindo numero nao existente")
    public void deletaTelefoneInexistente(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(EntidadeInexistenteExecption.class,()->telefoneService.deletaTelefone(Mockito.anyLong()));
        Mockito.verify(repository).existsById(Mockito.anyLong());
    }

    @ParameterizedTest
    @CsvSource(value = {"88,999759855"})
    @DisplayName(value = "teste atualizando telefone existente")
    public void atualizaTelefoneExistente(String dd, String numero){
        Telefone telefone = new Telefone(null,dd,numero,null);
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repository.save(telefone)).thenReturn(telefone);
        Assertions.assertNotNull(telefoneService.atualizaTelefone(telefone,Mockito.anyLong()));
        Mockito.verify(repository).existsById(Mockito.anyLong());
        Mockito.verify(repository).save(telefone);

    }

}

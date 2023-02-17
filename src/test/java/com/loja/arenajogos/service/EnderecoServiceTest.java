package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeExistenteException;
import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Endereco;
import com.loja.arenajogos.repository.EnderecoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderecoServiceTest {

    private EnderecoRepository repository;
    private EnderecoService enderecoService;
    private ArgumentCaptor<Object> argumentCaptor;
    @BeforeAll
    public void testeConfig(){
        this.repository = Mockito.mock(EnderecoRepository.class);
        this.enderecoService = new EnderecoService(repository);
    }

    @ParameterizedTest
    @CsvSource({"jefferson"})
    public void testeParametro(String nome){
        Assertions.assertEquals("jefferson",nome);
    }

    @Test
    public void testeSalvaEndereco(){
        argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.when(repository.existsByCidadeAndBairroAndRuaAndNumero(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(false);
        Endereco endereco = new Endereco(null,"ceara","limoeiro","centro","santos dumon","2406","propia",null);
        Assertions.assertDoesNotThrow(()-> {enderecoService.salvaEndereco(endereco);});
        Mockito.verify(repository,Mockito.times(2)).existsByCidadeAndBairroAndRuaAndNumero((String) argumentCaptor.capture(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());

    }
    @Test
    public void testeSalvaEnderecoExistente(){
        argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.when(repository.existsByCidadeAndBairroAndRuaAndNumero(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        Endereco endereco = new Endereco(null,"ceara","limoeiro","centro","santos dumon","2406","propia",null);
        Assertions.assertThrows(EntidadeExistenteException.class,()->{enderecoService.salvaEndereco(endereco);});
        Mockito.verify(repository).existsByCidadeAndBairroAndRuaAndNumero((String) argumentCaptor.capture(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
    }
    @Test
    public void testeBuscaTodos(){
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<Endereco>(Arrays.asList(new Endereco(),new Endereco(),new Endereco())));
        Assertions.assertTrue(!enderecoService.buscaTodos().isEmpty());
        Mockito.verify(repository).findAll();
    }
    @Test
    public void testeBuscaEnderecoExistente(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Endereco()));
        Assertions.assertNotNull(enderecoService.buscaEndereco(Mockito.anyLong()));
        Mockito.verify(repository,Mockito.times(2)).findById(Mockito.anyLong());
    }

    @Test
    public void testeBuscaEnderecoNaoExistente(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(enderecoService.buscaEndereco(Mockito.anyLong()));
        Mockito.verify(repository).findById(Mockito.anyLong());
    }

    @Test
    public void testeDeletaEntidade(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->{enderecoService.deletaEndereco(Mockito.anyLong());});
        Mockito.verify(repository).deleteById(Mockito.anyLong());
    }

    @Test
    public void testeDeletaEntidadeInexistente(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(EntidadeInexistenteExecption.class,()->{enderecoService.deletaEndereco(Mockito.anyLong());});

    }

    @Test
    public void testeAtualizaEndereco(){
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->{enderecoService.atualizaEndereco(new Endereco(null,"ceara","limoeiro","centro","santos dumon","2406","propia",null),Mockito.anyLong());});
    }

    @Test
    public void testeAtualizaEnderecoInexistente(){
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Assertions.assertThrows(EntidadeInexistenteExecption.class,()->{enderecoService.atualizaEndereco(new Endereco(null,"ceara","limoeiro","centro","santos dumon","2406","propia",null),Mockito.anyLong());});
    }

}

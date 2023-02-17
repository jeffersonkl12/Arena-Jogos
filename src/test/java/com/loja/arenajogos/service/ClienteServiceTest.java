package com.loja.arenajogos.service;

import com.loja.arenajogos.exceptions.EntidadeInexistenteExecption;
import com.loja.arenajogos.model.Cliente;
import com.loja.arenajogos.model.Endereco;
import com.loja.arenajogos.model.Telefone;
import com.loja.arenajogos.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteServiceTest {
   private ClienteRepository repository;
   private TelefoneService telefoneService;
   private EnderecoService enderecoService;
   private ClienteService clienteService;

    @BeforeAll
    private void confiurationContextMockito(){
        repository = Mockito.mock(ClienteRepository.class);
        enderecoService = Mockito.mock(EnderecoService.class);
        telefoneService = Mockito.mock(TelefoneService.class);
        Mockito.when(repository.save(Mockito.eq(new Cliente()))).thenReturn(new Cliente());
        clienteService = new ClienteService(repository,enderecoService,telefoneService);
    }
    @Test
    public void testeSalvarCliente(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(new Cliente());
        Endereco endereco = new Endereco(null,"ceara","limoeiro","centro","santos","2406","nenhum",null);
        Telefone telefone = new Telefone(null,"88","99759855",null);
        Cliente novo = new Cliente(null,"douglas", LocalDate.now(), Cliente.SEXO.MASCULINO,"07251370379",Mockito.mock(Endereco.class)
                ,Mockito.mock(Telefone.class));

        Assertions.assertTrue(clienteService.salvaCliente(novo) instanceof Cliente);
        Mockito.verify(repository).save(novo);
    }

    @Test
    public void testeBuscaTodosCliente(){
        Assertions.assertDoesNotThrow(()->{clienteService.buscaTodos();});
    }
    @Test
    public void testeBuscaCliente(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Cliente()));
        Assertions.assertNotNull(clienteService.buscaCliente(Mockito.anyLong()));
        Mockito.verify(repository,Mockito.times(2)).findById(Mockito.anyLong());
    }
    @Test
    public void testeBuscaClienteNaoExiste(){
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        Assertions.assertNull(clienteService.buscaCliente(Mockito.anyLong()));
        Mockito.verify(repository).findById(Mockito.anyLong());
    }
    @Test
    public void testeDeletaCliente(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->clienteService.deletaCliente(Mockito.anyLong()));
    }
    @Test
    public void testeDeletaClienteNaoExiste(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(EntidadeInexistenteExecption.class,()->{clienteService.deletaCliente(Mockito.anyLong());});
    }

    @Test
    public void testeAtualizaCliente(){
        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->{clienteService.atualizaCliente(new Cliente(),Mockito.anyLong());});
    }
}

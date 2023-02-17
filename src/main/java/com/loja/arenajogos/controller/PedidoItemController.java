package com.loja.arenajogos.controller;

import com.loja.arenajogos.model.Jogo;
import com.loja.arenajogos.model.Pedido;
import com.loja.arenajogos.model.PedidoItem;
import com.loja.arenajogos.model.PedidoItemId;
import com.loja.arenajogos.service.PedidoItemService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pedidoItem")
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItemController {

    @Autowired
    private PedidoItemService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PedidoItem> salvaPedidoItem(@RequestBody PedidoItem novo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvaPedidoItem(novo));
    }

    @RequestMapping
    public ResponseEntity<List<PedidoItem>> buscaTodos(){
        return ResponseEntity.ok(service.buscaTodos());
    }

    @RequestMapping(params = {"pedido","jogo"})
    public ResponseEntity<PedidoItem> buscaPedidoItem(@RequestParam(name = "pedido") Long pedidoId ,@RequestParam(name = "jogo") Long jogoId){
        PedidoItemId id = formaId(jogoId,pedidoId);

        return ResponseEntity.ok(service.buscaPedidoItem(id));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> deletaPedidoItem(@RequestParam(name = "pedido") Long pedidoId ,@RequestParam(name = "jogo") Long jogoId){
        PedidoItemId id = formaId(jogoId,pedidoId);

        service.delePedidoItem(id);
        return ResponseEntity.ok().build();
    }

    private PedidoItemId formaId(Long jogoId, Long pedidoId){
        PedidoItemId id = new PedidoItemId();
        Jogo jogo = new Jogo();
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Jogo.class,"id"));
        ReflectionUtils.setField(ReflectionUtils.findField(Jogo.class,"id"),jogo,jogoId);
        Pedido pedido = new Pedido();
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Pedido.class,"id"));
        ReflectionUtils.setField(ReflectionUtils.findField(Pedido.class,"id"),pedido,pedidoId);

        id.setProduto(jogo);
        id.setPedido(pedido);

        return id;
    }

}

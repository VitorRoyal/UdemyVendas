package br.com.udemy.vendas.domain.controller;

import br.com.udemy.vendas.domain.dto.AtualizacaoStatusPedidoDTO;
import br.com.udemy.vendas.domain.dto.InformacoesItemPedidoDTO;
import br.com.udemy.vendas.domain.dto.InformacoesPedidoDTO;
import br.com.udemy.vendas.domain.dto.PedidoDTO;
import br.com.udemy.vendas.domain.entity.Pedido;
import br.com.udemy.vendas.domain.entity.StatusPedido;
import br.com.udemy.vendas.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedidoSalvo = pedidoService.salvar(dto);
        return pedidoSalvo.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService.getPedidoCompleto(id);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
    }
}

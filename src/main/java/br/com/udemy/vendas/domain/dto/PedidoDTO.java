package br.com.udemy.vendas.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoDTO(Integer cliente, BigDecimal total, List<ItemPedidoDTO> itens, Integer quantidade) {
    // VEM O ID DO CLIENTE, O TOTAL DO PEDIDO, UMA LISTA DE ITENS E A QUANTIDADE DE PRODUTOS

    public Integer getCliente() {
        return cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}

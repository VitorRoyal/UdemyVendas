package br.com.udemy.vendas.domain.service;

import br.com.udemy.vendas.domain.dto.InformacoesItemPedidoDTO;
import br.com.udemy.vendas.domain.dto.InformacoesPedidoDTO;
import br.com.udemy.vendas.domain.dto.PedidoDTO;
import br.com.udemy.vendas.domain.entity.ItemPedido;
import br.com.udemy.vendas.domain.entity.Pedido;
import br.com.udemy.vendas.domain.entity.StatusPedido;
import br.com.udemy.vendas.domain.repository.ClienteRepository;
import br.com.udemy.vendas.domain.repository.ItemPedidoRepository;
import br.com.udemy.vendas.domain.repository.PedidoRepository;
import br.com.udemy.vendas.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido salvar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findById(dto.getCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
        pedido.setTotal(dto.getTotal());
        pedido.setStatusPedido(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedido = dto.getItens().stream()
                .map(itemDto -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produtoRepository.findById(itemDto.getProduto())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado")));
                    itemPedido.setQuantidade(itemDto.getQuantidade());
                    return itemPedido;
                }).collect(Collectors.toList());

        pedido.setItens(itensPedido);
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedido);

        return pedido;
    }

    public Optional<Pedido> ObterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    public InformacoesPedidoDTO getPedidoCompleto(Integer id) {
        return ObterPedidoCompleto(id)
                .map(p -> InformacoesPedidoDTO
                        .builder()
                        .codigo(p.getId())
                        .cpf(p.getCliente().getCpf())
                        .nomeCliente(p.getCliente().getNome())
                        .total(p.getTotal())
                        .status(p.getStatusPedido().name())
                        .itens(p.getItens()
                                .stream()
                                .map(item -> InformacoesItemPedidoDTO
                                        .builder()
                                        .descricaoProduto(item.getProduto().getDescricao())
                                        .precoUnitario(item.getProduto().getPreco())
                                        .quantidade(item.getQuantidade())
                                        .build())
                                .toList())
                        .build())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatusPedido(statusPedido);
                    return pedidoRepository.save(pedido);
                }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}

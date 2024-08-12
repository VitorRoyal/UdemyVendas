package br.com.udemy.vendas.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Cliente cliente;
    private BigDecimal total;
    private LocalDate dataPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Integer id, Cliente cliente, BigDecimal total, LocalDate dataPedido) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
        this.dataPedido = dataPedido;
    }

    public Integer getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public LocalDate getDataPedido() {
        return this.dataPedido;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", total=" + total +
                ", dataPedido=" + dataPedido +
                '}';
    }
}

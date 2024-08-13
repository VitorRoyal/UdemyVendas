package br.com.udemy.vendas.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "{campo.descricao.obrigatorio}") // NotEmpty valida se o campo é nulo ou vazio
    private String descricao;
    @Column(name = "preco_unitario")
    @NotNull(message = "{campo.preco.obrigatorio}") // NotNull valida se o campo é nulo
    private BigDecimal preco;

}

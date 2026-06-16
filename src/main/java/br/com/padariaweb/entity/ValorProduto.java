package br.com.padariaweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "valor_produto", schema = "public")
public class ValorProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valor_produto_seq")
    @SequenceGenerator(
        name = "valor_produto_seq",
        sequenceName = "valor_produto_sq_valor_produto_seq",
        allocationSize = 1
    )
    @Column(name = "sq_valor_produto", nullable = false)
    private @Getter @Setter Long sqValorProduto;

    @Column(name = "preco")
    private @Getter @Setter BigDecimal preco;

    @Column(name = "desconto")
    private @Getter @Setter BigDecimal desconto;

    @ManyToOne
    @JoinColumn(name = "fk_produto")
    private @Getter @Setter Produto produto;

    public ValorProduto() {
    }
}
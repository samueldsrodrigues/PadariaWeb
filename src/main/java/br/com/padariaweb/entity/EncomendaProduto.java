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
@Table(name = "encomenda_produto", schema = "public")
public class EncomendaProduto implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "encomenda_produto_seq")
	@SequenceGenerator(name = "encomenda_produto_seq", sequenceName = "encomenda_produto_sq_encomenda_produto_seq", allocationSize = 1)
	@Column(name = "sq_encomenda_produto", nullable = false)
	private @Getter @Setter Long sqEncomendaProduto;

	@ManyToOne
	@JoinColumn(name = "fk_encomenda")
	private @Getter @Setter Encomenda encomenda;

	@ManyToOne
	@JoinColumn(name = "fk_produto")
	private @Getter @Setter Produto produto;

	@Column(name = "nome_item")
	private @Getter @Setter String nomeItem;

	@Column(name = "quantidade")
	private @Getter @Setter Integer quantidade;

	@Column(name = "preco_unitario")
	private @Getter @Setter BigDecimal precoUnitario;

	@Column(name = "subtotal")
	private @Getter @Setter BigDecimal subtotal;

	@Column(name = "observacao")
	private @Getter @Setter String observacao;

	public EncomendaProduto() {
	}

	public EncomendaProduto(Encomenda encomenda, Produto produto, String nomeItem, Integer quantidade,
			BigDecimal precoUnitario, BigDecimal subtotal, String observacao) {
		this.encomenda = encomenda;
		this.produto = produto;
		this.nomeItem = nomeItem;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.subtotal = subtotal;
		this.observacao = observacao;
	}

}

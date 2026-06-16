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
@Table(name = "venda_produto", schema = "public")
public class VendaProduto implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_produto_seq")
	@SequenceGenerator(
	    name = "venda_produto_seq",
	    sequenceName = "venda_produto_sq_venda_produto_seq",
	    allocationSize = 1
	)
	@Column(name = "sq_venda_produto", nullable = false)
	private @Getter @Setter Long sqVendaProduto;
	
	@ManyToOne
    @JoinColumn(name = "fk_venda")
	private @Getter @Setter Venda venda;
	
	@ManyToOne
    @JoinColumn(name = "fk_produto")
	private @Getter @Setter Produto produto;
	
	@Column(name = "quantidade")
	private @Getter @Setter Integer quantidade;

	@Column(name = "preco_unitario")
	private @Getter @Setter BigDecimal precoUnitario;
	
	@Column(name = "subtotal")
	private @Getter @Setter BigDecimal subtotal;

	public VendaProduto() {
	}

	public VendaProduto(Venda venda, Produto produto, Integer quantidade, BigDecimal precoUnitario, BigDecimal subtotal) {
	    this.venda = venda;
	    this.produto = produto;
	    this.quantidade = quantidade;
	    this.precoUnitario = precoUnitario;
	    this.subtotal = subtotal;
	}

	
	
	
	

}

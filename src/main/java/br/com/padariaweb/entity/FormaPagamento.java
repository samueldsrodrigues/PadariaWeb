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
@Table(name = "forma_pagamento", schema = "public")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forma_pagamento_seq")
	@SequenceGenerator(
	    name = "forma_pagamento_seq",
	    sequenceName = "forma_pagamento_sq_forma_pagamento_seq",
	    allocationSize = 1
	)
	@Column(name = "sq_forma_pagamento", nullable = false)
	private @Getter @Setter Long sqFormaPagamento;
	
	@ManyToOne
	@JoinColumn(name = "fk_venda")
	private @Getter @Setter Venda venda;

	@Column(name = "tipo", length = 50)
	private @Getter @Setter String tipo;
	
	@Column(name = "bandeira", length = 50)
	private @Getter @Setter String bandeira;
	
	@Column(name = "qtd_parcelas")
	private @Getter @Setter Integer qtdParcelas;
	
	@Column(name = "valor")
	private @Getter @Setter BigDecimal valor;

	public FormaPagamento() {
	}

	public FormaPagamento(Venda venda, String tipo, String bandeira, Integer qtdParcelas, BigDecimal valor) {
		this.venda = venda;
		this.tipo = tipo;
		this.bandeira = bandeira;
		this.qtdParcelas = qtdParcelas;
		this.valor = valor;
	}
	
	



}

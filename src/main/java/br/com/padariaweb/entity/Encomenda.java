package br.com.padariaweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "encomenda", schema = "public")
public class Encomenda implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sq_encomenda", nullable = false)
	private @Getter @Setter Long sqEncomenda;

	@Column(name = "valor_entrada")
	private @Getter @Setter BigDecimal valorEntrada;

	@Column(name = "valor_total")
	private @Getter @Setter BigDecimal valorTotal;

	@Column(name = "dt_encomenda")
	private @Getter @Setter Date dtEncomenda;

	@Column(name = "dt_retirada")
	private @Getter @Setter Date dtRetirada;

	@Column(name = "observacao", length = 500)
	private @Getter @Setter String observacao;

	@Column(name = "status", length = 30)
	private @Getter @Setter String status;

	@ManyToOne
	@JoinColumn(name = "fk_cliente", nullable = false)
	private @Getter @Setter Cliente cliente;

	public Encomenda() {
	}

	public Encomenda(BigDecimal valorEntrada, BigDecimal valorTotal, Date dtEncomenda, Date dtRetirada,
			String observacao, String status, Cliente cliente) {
		this.valorEntrada = valorEntrada;
		this.valorTotal = valorTotal;
		this.dtEncomenda = dtEncomenda;
		this.dtRetirada = dtRetirada;
		this.observacao = observacao;
		this.status = status;
		this.cliente = cliente;
	}

}

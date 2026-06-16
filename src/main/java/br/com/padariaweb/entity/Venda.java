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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venda", schema = "public")
public class Venda implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
	@SequenceGenerator(name = "venda_seq", sequenceName = "venda_sq_venda_seq", allocationSize = 1)
	@Column(name = "sq_venda", nullable = false)
	private @Getter @Setter Long sqVenda;

	@Column(name = "vl_total")
	private @Getter @Setter BigDecimal vlTotal;

	@Column(name = "dt_venda")
	private @Getter @Setter Date dtVenda;

	@Column(name = "status")
	private @Getter @Setter String status;

	@ManyToOne
	@JoinColumn(name = "fk_funcionario")
	private @Getter @Setter Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "fk_cliente")
	private @Getter @Setter Cliente cliente;

	public Venda() {
	}

	public Venda(BigDecimal vlTotal, Date dtVenda, Funcionario funcionario, Cliente cliente) {
		this.vlTotal = vlTotal;
		this.dtVenda = dtVenda;
		this.funcionario = funcionario;
		this.cliente = cliente;
	}

}

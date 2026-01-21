package br.com.padariaweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "venda", schema = "public")
public class Venda implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sq_venda", nullable = false)
	private @Getter @Setter Long sqVenda;

	@Column(name = "vl_total")
	private @Getter @Setter BigDecimal vlTotal;

	@Column(name = "dt_venda")
	private @Getter @Setter LocalDateTime dtVenda;

	@ManyToOne
    @JoinColumn(name = "fk_funcionario")
	private @Getter @Setter Funcionario fkFuncionario;

	public Venda() {
	}

	public Venda(BigDecimal vlTotal, LocalDateTime dtVenda, Funcionario fkFuncionario) {
		this.vlTotal = vlTotal;
		this.dtVenda = dtVenda;
		this.fkFuncionario = fkFuncionario;
	}
	
	

}

package br.com.padariaweb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

	@Column(name = "dt_retirada")
	private @Getter @Setter LocalDateTime dtRetirada;

	@ManyToOne
    @JoinColumn(name = "fk_cliente")
	private @Getter @Setter Cliente fkCliente;

	public Encomenda() {
	}

	public Encomenda(BigDecimal valorEntrada, LocalDateTime dtRetirada, Cliente fkCliente) {
		this.valorEntrada = valorEntrada;
		this.dtRetirada = dtRetirada;
		this.fkCliente = fkCliente;
	}
	
	

	
	
	

}

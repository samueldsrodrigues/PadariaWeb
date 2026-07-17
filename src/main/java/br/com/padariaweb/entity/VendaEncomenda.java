package br.com.padariaweb.entity;

import java.io.Serializable;

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
@Table(name = "venda_encomenda", schema = "public")
public class VendaEncomenda implements Serializable {

	private static final long serialVersionUID = -6005500842688399719L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_encomenda_seq")
	@SequenceGenerator(name = "venda_encomenda_seq", sequenceName = "venda_encomenda_sq_venda_encomenda_seq", allocationSize = 1)
	@Column(name = "sq_venda_encomenda", nullable = false)
	private @Getter @Setter Long sqVendaEncomenda;

	@ManyToOne
	@JoinColumn(name = "fk_venda", nullable = false)
	private @Getter @Setter Venda venda;

	@ManyToOne
	@JoinColumn(name = "fk_encomenda", nullable = false)
	private @Getter @Setter Encomenda encomenda;

	public VendaEncomenda() {
	}

	public VendaEncomenda(Venda venda, Encomenda encomenda) {
		this.venda = venda;
		this.encomenda = encomenda;
	}

}

package br.com.padariaweb.entity;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "turno", schema = "public")
@EqualsAndHashCode(of = { "sqTurno" })
public class Turno implements Serializable {

	private static final long serialVersionUID = -4374392355937267297L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sq_turno", unique = true, nullable = false)
	private @Getter @Setter Long sqTurno;

	@Column(name = "hr_entrada")
	private @Getter @Setter LocalTime hrEntrada;

	@Column(name = "hr_saida")
	private @Getter @Setter LocalTime hrSaida;

	public Turno() {
	}

	public Turno(LocalTime hrEntrada, LocalTime hrSaida) {
		this.hrEntrada = hrEntrada;
		this.hrSaida = hrSaida;
	}

}

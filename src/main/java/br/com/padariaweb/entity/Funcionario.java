package br.com.padariaweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario", schema = "public")
@EqualsAndHashCode(of = { "sqFuncionario" })
public class Funcionario implements Serializable {

	private static final long serialVersionUID = -7959647335026358771L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sq_funcionario", unique = true, nullable = false)
	private @Getter @Setter Long sqFuncionario;

	@Column(name = "nome", length = 50)
	private @Getter @Setter String nome;

	@Column(name = "idade")
	private @Getter @Setter Integer idade;

	@Column(name = "email", length = 50)
	private @Getter @Setter String email;

	@Column(name = "senha", length = 50)
	private @Getter @Setter String senha;

	@Column(name = "semente", length = 100)
	private @Getter @Setter String semente;

	@Column(name = "dia_folga", length = 20)
	private @Getter @Setter String diaFolga;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_turno")
	private @Getter @Setter Turno turno;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_cargo")
	private @Getter @Setter Cargo cargo;

	public Funcionario() {
	}

	public Funcionario(String nome, String email, String senha, Cargo cargo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cargo = cargo;
	}

}

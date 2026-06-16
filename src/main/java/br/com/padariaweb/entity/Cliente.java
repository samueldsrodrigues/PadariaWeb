package br.com.padariaweb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente", schema = "public")
@EqualsAndHashCode(of = { "sqCliente" })
public class Cliente implements Serializable {

	private static final long serialVersionUID = 3644353087148961752L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(
	    name = "cliente_seq",
	    sequenceName = "cliente_sq_cliente_seq",
	    allocationSize = 1
	)
	@Column(name = "sq_cliente", unique = true, nullable = false)
	private @Getter @Setter Long sqCliente;

	@Column(name = "nome", length = 50)
	private @Getter @Setter String nome;

	@Column(name = "telefone")
	private @Getter @Setter String telefone;

	@Column(name = "cpf")
	private @Getter @Setter String cpf;

	@Column(name = "email")
	private @Getter @Setter String email;

	public Cliente() {
	}

	public Cliente(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

}

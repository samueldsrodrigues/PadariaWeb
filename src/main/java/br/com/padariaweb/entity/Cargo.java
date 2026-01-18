package br.com.padariaweb.entity;

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
@Table(name = "cargo", schema = "public")
@EqualsAndHashCode(of = { "sqCargo" })
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sq_cargo", unique = true, nullable = false)
	private @Getter @Setter Integer sqCargo;
	
	@Column(name = "nome", length = 50)
	private @Getter @Setter String nome;
}

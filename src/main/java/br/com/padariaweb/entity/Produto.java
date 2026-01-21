package br.com.padariaweb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto", schema = "public")  
public class Produto implements java.io.Serializable {

	private static final long serialVersionUID = 8397836139772841238L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sq_produto", unique = false, nullable = false)
	private @Getter @Setter Long sqProduto;
	
	@Column(name = "nome")
	private @Getter @Setter String nome;
	
	@Column(name = "tipo")
	private @Getter @Setter String tipo;
	
	@Column(name = "descricao")
	private @Getter @Setter String descricao;
	
	@Column(name = "dt_validade")
	private @Getter @Setter Date dtValidade;
	
	@Column(name = "estoque")
	private @Getter @Setter Integer estoque;
	
	
	public Produto() {}


	public Produto(String nome, String tipo, String descricao, Date dtValidade, Integer estoque) {
		this.nome = nome;
		this.tipo = tipo;
		this.descricao = descricao;
		this.dtValidade = dtValidade;
		this.estoque = estoque;
	}
	
	
	
	
	
}

package com.generation.lojagames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome da categoria é obrigatório")
	@Size(min = 5, max = 100, message = "É necessário no minimo 5 caracteres e no máximo 100")
	private String nomecategoria;
	
	@NotBlank(message = "Descrição obrigatória")
	private String descricaocategoria;
	
	@OneToMany(fetch = FetchType.LAZY,  mappedBy  = "categorias", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categorias")
	private List<Produto> produtos;
	
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nomecategoria;
	}

	public void setNome(String nome) {
		this.nomecategoria = nome;
	}

	public String getDescricao() {
		return descricaocategoria;
	}

	public void setDescricao(String descricao) {
		this.descricaocategoria = descricao;
	}
	
	
	
}

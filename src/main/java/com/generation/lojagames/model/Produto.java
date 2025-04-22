package com.generation.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String foto;
	
	@NotBlank(message = "O nome do jogo é obrigatório")
	@Size(min = 1, max = 100, message = "É necessário no minimo 2 caracteres e no máximo 100")
	private String nomejogo;
	
	@NotBlank(message = "Plataforma obrigatória")
	@Size(min = 2, max = 100, message = "É necessário no minimo 2 caracteres e no máximo 100")
	private String plataforma;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "data de lançamento obrigatória")
	private LocalDate dataLancamento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotBlank(message = "Preço obrigatório")
	@Positive(message = "O preço precisa ser maior que zero!")
	private BigDecimal preco;
	
	@NotBlank(message = "Descrição obrigatória")
	private String descricaojogo;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categorias;
	

	public Categoria getCategorias() {
		return categorias;
	}

	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNomejogo() {
		return nomejogo;
	}

	public void setNomejogo(String nomejogo) {
		this.nomejogo = nomejogo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getDescricaojogo() {
		return descricaojogo;
	}

	public void setDescricaojogo(String descricaojogo) {
		this.descricaojogo = descricaojogo;
	}

	
	
}

package model.entities;

import java.time.LocalDate;

import model.entities.enums.Cor;

public class Carro {
	private Integer id;
	private String modelo;
	private String placa;
	private Cor cor;
	private Integer ano;
	private LocalDate dataAquisicao;
	
	private Categoria categoria;
	
	public Carro() {}

	public Carro(Integer id, String modelo, String placa, Cor cor, Integer ano, LocalDate dataAquisicao, Categoria categoria) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
		this.ano = ano;
		this.dataAquisicao = dataAquisicao;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public Categoria getCategoriaId() {
		return categoria;
	}

	public void setCategoriaId(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", cor=" + cor + ", ano=" + ano
				+ ", dataAquisicao=" + dataAquisicao + ", " + categoria;
	}
	
	
	
	
	
}

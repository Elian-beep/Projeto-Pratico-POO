package model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Locacao {
	private Integer id;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucao;
	private Carro carroId;
	private Cliente clienteId;
	
	public Locacao() {}

	public Locacao(Integer id, LocalDate dataRetirada, LocalDate dataDevolucao, Carro carroId,
			Cliente clienteId) {
		super();
		this.id = id;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.carroId = carroId;
		this.clienteId = clienteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Carro getCarroId() {
		return carroId;
	}

	public void setCarroId(Carro carroId) {
		this.carroId = carroId;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", dataRetirada=" + dataRetirada + ", dataDevolucao=" + dataDevolucao
				+ ", carroId=" + carroId.getId() + ", clienteId=" + clienteId.getId() + "]";
	}

	
	
	
	
	
}

package model.entities;

import java.time.LocalDate;

public class LocacaoDiaria extends Locacao{
	private Integer diasPrevistoDevolucao;
	
	public LocacaoDiaria() {}

	public LocacaoDiaria(Integer id, LocalDate dataRetirada, LocalDate dataDevolucao, Carro carroId,
			Cliente clienteId, Integer diasPrevistoDevolucao) {
		super(id, dataRetirada, dataDevolucao, carroId, clienteId);
		this.diasPrevistoDevolucao = diasPrevistoDevolucao;
	}

	public Integer getDiasPrevistoDevolucao() {
		return diasPrevistoDevolucao;
	}

	public void setDiasPrevistoDevolucao(Integer diasPrevistoDevolucao) {
		this.diasPrevistoDevolucao = diasPrevistoDevolucao;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + super.getId()
			+ ", dataRetirada=" + super.getDataRetirada()
			+ ", dataDevolucao=" + super.getDataDevolucao()
			+ ", carroId=" + super.getCarroId().getId()
			+ ", clienteId=" + super.getClienteId().getId()
			+ ", diasPrevistoDevolucao="+getDiasPrevistoDevolucao()
			+ ", clienteNome=" +super.getClienteId().getNome()
			+ ", modeloCarro=" +super.getCarroId().getModelo()
			+"]";
	}

	
	
	
}

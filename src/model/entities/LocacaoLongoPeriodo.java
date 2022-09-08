package model.entities;

import java.time.LocalDate;

public class LocacaoLongoPeriodo extends Locacao{
	private Double porcentagemDesconto;
	
	public LocacaoLongoPeriodo() {}

	public LocacaoLongoPeriodo(Integer id, LocalDate dataRetirada, LocalDate dataDevolucao, Carro carroId,
			Cliente clienteId, Double porcentagemDesconto) {
		super(id, dataRetirada, dataDevolucao, carroId, clienteId);
		this.porcentagemDesconto = porcentagemDesconto;
	}

	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + super.getId()
			+ ", dataRetirada=" + super.getDataRetirada()
			+ ", dataDevolucao=" + super.getDataDevolucao()
			+ ", carroId=" + super.getCarroId().getId()
			+ ", clienteId=" + super.getClienteId().getId()
			+ ", porcentagemDesconto="+getPorcentagemDesconto()
			+ ", clienteNome=" +super.getClienteId().getNome()
			+ ", modeloCarro=" +super.getCarroId().getModelo()
			+"]";
	}
	
	
}

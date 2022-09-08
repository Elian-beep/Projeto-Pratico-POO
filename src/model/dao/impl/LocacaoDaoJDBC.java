package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.LocacaoDao;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.Cliente;
import model.entities.Locacao;
import model.entities.LocacaoDiaria;
import model.entities.LocacaoLongoPeriodo;
import model.entities.enums.Cor;

public class LocacaoDaoJDBC implements LocacaoDao {

	private Connection conn;

	public LocacaoDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insert(LocacaoDiaria locacaoDiaria, LocacaoLongoPeriodo locacaoLongo, boolean tipoDiaria) {
		PreparedStatement st = null;
		try {
			if (tipoDiaria) {
				st = conn.prepareStatement(
						"INSERT INTO Locacao(dataRetirada, dataDevolucao, diasPrevistoDevolucao, porcentagemDesconto, cliente_id, carro_id) VALUES (?, ?, ?, null, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				st.setDate(1, Date.valueOf(locacaoDiaria.getDataRetirada()));
				st.setDate(2, Date.valueOf(locacaoDiaria.getDataDevolucao()));
				st.setInt(3, locacaoDiaria.getDiasPrevistoDevolucao());
				st.setInt(4, locacaoDiaria.getClienteId().getId());
				st.setInt(5, locacaoDiaria.getCarroId().getId());
			} else if (!tipoDiaria) {
				st = conn.prepareStatement(
						"INSERT INTO Locacao(dataRetirada, dataDevolucao, diasPrevistoDevolucao, porcentagemDesconto, cliente_id, carro_id) VALUES (?, ?, null, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				st.setDate(1, Date.valueOf(locacaoLongo.getDataRetirada()));
				st.setDate(2, Date.valueOf(locacaoLongo.getDataDevolucao()));
				st.setDouble(3, locacaoLongo.getPorcentagemDesconto());
				st.setInt(4, locacaoLongo.getClienteId().getId());
				st.setInt(5, locacaoLongo.getCarroId().getId());
			}

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					if (tipoDiaria) {
						locacaoDiaria.setId(id);
					} else if (!tipoDiaria) {
						locacaoLongo.setId(id);
					}
				}
			} else {
				throw new DbException("Erro: Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Locacao> findAll() { // ----------------FUNÇÃO FALHA-----------------------
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement("SELECT Locacao.*, Cliente.nome AS NomeCli, Carro.modelo AS ModeloCar FROM Locacao INNER JOIN Cliente ON Cliente.id = Locacao.cliente_id INNER JOIN Carro ON Carro.id = Locacao.carro_id;");
//			rs = st.executeQuery();
//
//			List<Locacao> list = new ArrayList<>();
//			Map<Integer, Cliente> mapCli = new HashMap<>();
//			Map<Integer, Carro> mapCar = new HashMap<>();
//			Map<Integer, Categoria> mapCat = new HashMap<>();
//
//			while (rs.next()) {
//				Categoria cat = mapCat.get(rs.getInt("Carro.categoria_id"));
//				Carro car = mapCar.get(rs.getInt("carro_id"));
//				Cliente cli = mapCli.get(rs.getInt("cliente_id"));
//				
//				if (cat == null) {
//					cat = instantiateCategoria(rs);
//					mapCat.put(rs.getInt("Carro.categoria_id"), cat);
//				}
//				
//				if (car == null) {
//					car = instantiateCarro(rs, cat);
//					mapCat.put(rs.getInt("carro_id"), cat);
//				}
//				
//				if (cli == null) {
//					cli = instantiateCliente(rs);
//					mapCli.put(rs.getInt("cliente_id"), cli);
//				}
//				
//				if (tipoDiaria) {
//					LocacaoDiaria locacao = instantiateLocacaoDiaria(rs, car, cli);
//					list.add(locacao);
//				}else {
//					LocacaoLongoPeriodo locacao = instantiateLocacaoLonga(rs, car, cli);
//					list.add(locacao);
//				}
//				
//			}
//			return list;
//		}
//		catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		}
//		finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
		return null;
	}

	@Override
	public Locacao findByIdCliente(Cliente cliente) { // ----------------FUNÇÃO FALHA-----------------------
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		try {
//			st = conn.prepareStatement(
//					"SELECT Locacao.*, Cliente.nome AS NomeCli, Carro.modelo AS ModeloCar FROM Locacao INNER JOIN Cliente ON Cliente.id = Locacao.cliente_id INNER JOIN Carro ON Carro.id = Locacao.carro_id WHERE Locacao.cliente_id = ?;");
//			st.setInt(1, cliente.getId());
//			rs = st.executeQuery();
//
//			List<Carro> list = new ArrayList<>();
//			Map<Integer, Cliente> mapCli = new HashMap<>();
//			Map<Integer, Carro> mapCar = new HashMap<>();
//			Map<Integer, Categoria> map = new HashMap<>();
//
//			while (rs.next()) {
//				Categoria cat = map.get(rs.getInt("categoria_id"));
//
//				if (cat == null) {
//					cat = instantiateCategoria(rs);
//					map.put(rs.getInt("categoria_id"), cat);
//				}
//
//				Carro obj = instantiateCarro(rs, cat);
//				list.add(obj);
//			}
//			return list;
//		} catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		} finally {
//			DB.closeStatement(st);
//			DB.closeResultSet(rs);
//		}
		
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Locacao WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Double Devolucao(LocacaoDiaria locacaoDiaria, LocacaoLongoPeriodo locacaoLongo, Double valorDiaria,
			boolean tipoDiaria) {
		if (tipoDiaria) {
			return locacaoDiaria.getDiasPrevistoDevolucao() * valorDiaria;
		} else {
			long diasAlugados = locacaoLongo.getDataRetirada().until(locacaoLongo.getDataDevolucao(), ChronoUnit.DAYS);
			double desconto = (diasAlugados * valorDiaria) * locacaoLongo.getPorcentagemDesconto();
			return (diasAlugados * valorDiaria) - desconto;
		}
	}

	@Override
	public Locacao findById(Integer id, boolean tipoDiaria) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Locacao.*, Cliente.nome AS NomeCli, Carro.modelo AS ModeloCar, Carro.id as CarId, Cliente.id as CliId FROM Locacao INNER JOIN Cliente ON Cliente.id = Locacao.cliente_id INNER JOIN Carro ON Carro.id = Locacao.carro_id WHERE Locacao.id = ?;");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				if (tipoDiaria) {
					Categoria cat = instantiateCategoria(rs);
					Carro car = instantiateCarro(rs, cat);
					Cliente cli = instantiateCliente(rs);
					LocacaoDiaria locacao = instantiateLocacaoDiaria(rs, car, cli);
					return locacao;
				} else {
					Categoria cat = instantiateCategoria(rs);
					Carro car = instantiateCarro(rs, cat);
					Cliente cli = instantiateCliente(rs);
					LocacaoLongoPeriodo locacao = instantiateLocacaoLonga(rs, car, cli);
					return locacao;
				}
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

//	INSTÂNCIANDO AS ENTIDADES DENTRO DA LOCAÇÃO
	private LocacaoDiaria instantiateLocacaoDiaria(ResultSet rs, Carro car, Cliente cli) throws SQLException {
		LocacaoDiaria locacao = new LocacaoDiaria();
		locacao.setId(rs.getInt("id"));
		locacao.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
		locacao.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
		locacao.setCarroId(car);
		locacao.setClienteId(cli);
		locacao.setDiasPrevistoDevolucao(rs.getInt("diasPrevistoDevolucao"));
		return locacao;
	}

	private LocacaoLongoPeriodo instantiateLocacaoLonga(ResultSet rs, Carro car, Cliente cli) throws SQLException {
		LocacaoLongoPeriodo locacao = new LocacaoLongoPeriodo();
		locacao.setId(rs.getInt("id"));
		locacao.setDataRetirada(rs.getDate("dataRetirada").toLocalDate());
		locacao.setDataDevolucao(rs.getDate("dataDevolucao").toLocalDate());
		locacao.setCarroId(car);
		locacao.setClienteId(cli);
		locacao.setPorcentagemDesconto(rs.getDouble("porcentagemDesconto"));
		return locacao;
	}

	private Cliente instantiateCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("CliId"));
		cliente.setNome(rs.getString("NomeCli"));
		return cliente;
	}

	private Carro instantiateCarro(ResultSet rs, Categoria cat) throws SQLException {
		Carro carro = new Carro();
		carro.setId(rs.getInt("CarId"));
		carro.setModelo(rs.getString("ModeloCar"));
		carro.setCategoriaId(cat);
		return carro;
	}

	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria cat = new Categoria();
		cat.setId(rs.getInt("id"));
		return cat;
	}

}

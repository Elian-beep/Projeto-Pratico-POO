package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.CarroDao;
import model.entities.Carro;
import model.entities.Categoria;
import model.entities.enums.Cor;

public class CarroDaoJDBC implements CarroDao{
	private Connection conn;

	public CarroDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insert(Carro carro) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Carro(modelo, placa, cor, ano, dataAquisicao, categoria_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, carro.getModelo());
			st.setString(2, carro.getPlaca());
			st.setString(3, carro.getCor().toString());
			st.setInt(4, carro.getAno());
			st.setDate(5, Date.valueOf(carro.getDataAquisicao()));
			st.setInt(6, carro.getCategoriaId().getId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					carro.setId(id);
				}
			} else {
				throw new DbException("Erro: Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Carro WHERE Id = ?");

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
	public void update(Carro carro) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Carro SET modelo=?, placa=?, cor=?, ano=?, dataAquisicao=?, categoria_id=? WHERE id = ?");
			
			st.setString(1, carro.getModelo());
			st.setString(2, carro.getPlaca());
			st.setString(3, carro.getCor().toString());
			st.setInt(4, carro.getAno());
			st.setDate(5, Date.valueOf(carro.getDataAquisicao()));
			st.setInt(6, carro.getCategoriaId().getId());
			st.setInt(7, carro.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

//	-------------------------TEM QUE VOLTAR AQUI
	@Override
	public Carro findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Carro.*, Categoria.descricao as CateDescri, Categoria.precoDiaria FROM Carro INNER JOIN Categoria ON Carro.categoria_id = Categoria.id WHERE Carro.id = ? ORDER BY Carro.id;");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria cat = instantiateCategoria(rs);
				Carro carro = instantiateCarro(rs, cat);
				return carro;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

//	-------------------------TEM QUE VOLTAR AQUI
	@Override
	public List<Carro> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Carro.*, Categoria.descricao as CateDescri, Categoria.precoDiaria FROM Carro INNER JOIN Categoria ON Carro.categoria_id = Categoria.id;");
			rs = st.executeQuery();

			List<Carro> list = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();

			while (rs.next()) {
				Categoria cat = map.get(rs.getInt("categoria_id"));
				
				if (cat == null) {
					cat = instantiateCategoria(rs);
					map.put(rs.getInt("categoria_id"), cat);
				}
				
				Carro carro = instantiateCarro(rs, cat);
				
				list.add(carro);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Carro> findByIdCategoria(Categoria categoria) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Carro.*, Categoria.descricao as CateDescri, Categoria.precoDiaria FROM Carro INNER JOIN Categoria ON Carro.categoria_id = Categoria.id WHERE categoria_id = ? ORDER BY Carro.id;");
			st.setInt(1, categoria.getId());
			rs = st.executeQuery();
			
			List<Carro> list = new ArrayList<>();
			Map<Integer, Categoria> map = new HashMap<>();
			
			while (rs.next()) {
				Categoria cat = map.get(rs.getInt("categoria_id"));
				
				if (cat == null) {
					cat = instantiateCategoria(rs);
					map.put(rs.getInt("categoria_id"), cat);
				}
				
				Carro obj = instantiateCarro(rs, cat);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
//	INSTÂNCIANDO AS ENTIDADES DENTRO DO SELLER
	private Carro instantiateCarro(ResultSet rs, Categoria cat) throws SQLException {
		Carro carro = new Carro();
		carro.setId(rs.getInt("id"));
		carro.setModelo(rs.getString("modelo"));
		carro.setPlaca(rs.getString("placa"));
		carro.setCor(Cor.valueOf(rs.getString("cor")));
		carro.setAno(rs.getInt("ano"));
		carro.setDataAquisicao(rs.getDate("dataAquisicao").toLocalDate());
		carro.setCategoriaId(cat);
		return carro;
	}
	
	private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
		Categoria cat = new Categoria();
		cat.setId(rs.getInt("categoria_id"));
		cat.setDescricao(rs.getString("CateDescri"));
		cat.setPrecoDiaria(rs.getDouble("precoDiaria"));
		return cat;
	}

}

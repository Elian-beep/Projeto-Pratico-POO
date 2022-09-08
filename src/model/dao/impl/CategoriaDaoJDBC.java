package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.CategoriaDao;
import model.entities.Categoria;
import model.entities.Cliente;

public class CategoriaDaoJDBC implements CategoriaDao{
	
	private Connection conn;

	public CategoriaDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insert(Categoria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Categoria(descricao, precoDiaria) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getPrecoDiaria());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
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
	public void update(Categoria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Categoria SET descricao=?, precoDiaria=? WHERE id = ?");
			
			st.setString(1, obj.getDescricao());
			st.setDouble(2, obj.getPrecoDiaria());
			st.setInt(3, obj.getId());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM Categoria WHERE Id = ?");

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
	public List<Categoria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM categoria ORDER BY id");
			rs = st.executeQuery();

			List<Categoria> list = new ArrayList<>();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setPrecoDiaria(rs.getDouble("precoDiaria"));
				list.add(categoria);
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
	public Categoria findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM Categoria WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setPrecoDiaria(rs.getDouble("precoDiaria"));
				return categoria;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}

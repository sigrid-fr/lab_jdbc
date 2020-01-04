package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Cidade;

public class CidadeDAO {
	private Connection conexao;
	
	public CidadeDAO(Connection conexao) throws SQLException{
		this.conexao = conexao;
	}
	
	public Cidade inserirCidade(Cidade cidade) throws SQLException{
		String sql = "INSERT INTO cidadetb (nome, UF, taxa) VALUES (?, ?, ?)";
		try(PreparedStatement stm = 
				conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, cidade.getNome());
			stm.setString(2, cidade.getUF());
			stm.setDouble(3, cidade.getTaxa());
			
			stm.execute();
			
			try(ResultSet keys = stm.getGeneratedKeys()){
				keys.next();
				int key = keys.getInt(1);
				cidade.setCodigo_cidade(key);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return null;
	}
	
	public ArrayList<Cidade> listarCidades() throws SQLException{
		ArrayList<Cidade> listacidades = new ArrayList<>();
		Statement stm = conexao.createStatement();
		String sql = "SELECT * from cidadetb";
		
		ResultSet result = stm.executeQuery(sql);
		 while (result.next()){
			 Cidade cidade = new Cidade();
			 cidade.setCodigo_cidade(result.getInt("codigo_cidade"));
			 cidade.setNome(result.getString("nome"));
			 cidade.setTaxa(result.getDouble("taxa"));
			 cidade.setUF(result.getString("Uf"));
			 listacidades.add(cidade);
		 }
		 return listacidades;
	}
	
	public Cidade listarCidadeMaisFretes() throws SQLException{
		String sql = "SELECT cidadetb.codigo_cidade, nome, uf, taxa, count(*) AS qtd FROM "
				+ "cidadetb JOIN fretetb ON cidadetb.codigo_cidade = fretetb.codigo_cidade "
				+ "GROUP BY nome "
				+ "ORDER BY qtd DESC LIMIT 1";
		Statement stm = conexao.createStatement();
		ResultSet result = stm.executeQuery(sql);
		result.next();
		Cidade cidade = new Cidade();
		cidade.setCodigo_cidade(result.getInt("codigo_cidade"));
		cidade.setNome(result.getString("nome"));
		cidade.setUF(result.getString("uf"));
		cidade.setTaxa(result.getDouble("taxa"));
		
		
		return cidade;
	}

}

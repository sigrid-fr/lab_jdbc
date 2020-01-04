package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import modelo.Cidade;
import modelo.Cliente;
import modelo.Frete;

public class FreteDAO {
	private Connection conexao;
	
	public FreteDAO(Connection conexao) throws SQLException{
		this.conexao = conexao;
	}
	
	public void inserirFrete(Frete frete) throws SQLException{
		String sql = "INSERT INTO freteTB (codigo_cidade, codigo_cliente, descricao,"
				+ " peso, valor) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stm = 
				conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, frete.getCidade().getCodigo_cidade());
			stm.setInt(2, frete.getCliente().getCodigo_cliente());
			stm.setString(3, frete.getDescricao());
			stm.setDouble(4, frete.getPeso());
			stm.setDouble(5, frete.getValor());
			
			stm.execute();
			
			try(ResultSet keys = stm.getGeneratedKeys()){
				keys.next();
				int key = keys.getInt(1);
				frete.setCodigo_frete(key);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public ArrayList<Frete> listarFretes() throws SQLException{
		ArrayList<Frete> listafretes = new ArrayList<>();
		String sql = "SELECT * from fretetb";
		Statement stm = conexao.createStatement();
		ResultSet result = stm.executeQuery(sql);
		 while (result.next()){
			 Frete frete = new Frete();
			 Cliente c = new Cliente();
			 c.setCodigo_cliente(result.getInt("codigo_cliente"));
			 Cidade ci = new Cidade();
			 ci.setCodigo_cidade(result.getInt("codigo_cidade"));
			 frete.setCliente(c);
			 frete.setCidade(ci);
			 frete.setCodigo_frete(result.getInt("codigo_frete"));
			 frete.setDescricao(result.getString("descricao"));
			 frete.setPeso(result.getDouble("peso"));
			 frete.setValor(result.getDouble("valor"));
			 listafretes.add(frete);
		 }
		 return listafretes;
	}
	
	
	
	
	public Frete freteMaisCaro() throws SQLException{
		Frete frete = new Frete();
		
		String sql = "SELECT * FROM fretetb WHERE valor IN (SELECT MAX(valor) as maior from fretetb)";
		Statement stm = conexao.createStatement();
		ResultSet result = stm.executeQuery(sql);
		result.next();
		frete.setCodigo_frete(result.getInt("codigo_frete"));
		Cliente c = new Cliente();
		Cidade ci = new Cidade();
		c.setCodigo_cliente(result.getInt("codigo_cliente"));
		ci.setCodigo_cidade(result.getInt("codigo_cidade"));
		frete.setCidade(ci);
		frete.setCliente(c);
		frete.setDescricao(result.getString("descricao"));
		frete.setPeso(result.getDouble("peso"));
		frete.setValor(result.getDouble("valor"));
		
		return frete;
	}
	
	
	
}

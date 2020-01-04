package dao;

import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO {
	
	private Connection conexao;
	
	public ClienteDAO(Connection conexao) throws SQLException{
		this.conexao = conexao;
	}
	
	public Cliente inserirCliente(Cliente cliente) throws SQLException{
		String sql = "INSERT INTO clienteTB (nome, endereco, telefone) VALUES (?, ?, ?)";
		try(PreparedStatement stm = 
				conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEndereco());
			stm.setString(3, cliente.getTelefone());
			
			stm.execute();
			
			try(ResultSet keys = stm.getGeneratedKeys()){
				keys.next();
				int key = keys.getInt(1);
				cliente.setCodigo_cliente(key);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	return null;
	}
	
	public ArrayList<Cliente> listarClientes() throws SQLException{
		ArrayList<Cliente> listaclientes = new ArrayList<>();
		String sql = "SELECT * from clientetb";
		Statement stm = conexao.createStatement();
		ResultSet result = stm.executeQuery(sql);
		 while (result.next()){
			 Cliente cliente = new Cliente();
			 cliente.setCodigo_cliente(result.getInt("codigo_cliente"));
			 cliente.setNome(result.getString("nome"));
			 cliente.setEndereco(result.getString("endereco"));
			 cliente.setTelefone(result.getString("telefone"));
			 listaclientes.add(cliente);
		 }
		 return listaclientes;
	}
	
	public JTable getRelatorioCliente(String cliente) throws SQLException{
		String [] colunas = {"Frete nº.", "Cidade nº.", "Cliente nº.", 
				"Descrição", "Peso (KG)", "Valor (R$)"};
		String[] dados = new String[6];
		DefaultTableModel modelo = new DefaultTableModel(null, colunas);
		
		String sql = "SELECT * FROM fretetb, clientetb WHERE fretetb.codigo_cliente = clientetb.codigo_cliente AND "
				+ "clientetb.nome LIKE '%" + cliente + "'";
		Statement stm = conexao.createStatement();
		ResultSet result = stm.executeQuery(sql);
		while(result.next()){
			dados[0] = result.getString("codigo_frete");
			dados[1] = result.getString("codigo_cidade");
			dados[2] = result.getString("codigo_cliente");
			dados[3] = result.getString("descricao");
			dados[4] = String.valueOf(result.getDouble("peso"));
			dados[5] = String.valueOf(result.getDouble("valor"));
			modelo.addRow(dados);
		}
		JTable table = new JTable(modelo);
		return table;
	}
}
